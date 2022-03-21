package com.keshmam.framework.ble

import android.annotation.SuppressLint
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.os.ParcelUuid
import com.keshmam.ble_client.api.BleClient
import com.keshmam.core.entities.ble.BleScannerStateDomainEntity
import com.keshmam.core.toSeconds
import com.keshmam.core.tracking.Logger
import com.keshmam.core.usecases.ble.OpenBlueToothRule
import com.keshmam.core.usecases.ble.SearchBLEDeviceRule
import com.keshmam.framework.toDomainEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.properties.Delegates



internal class SearchBLEDeviceRuleImpl(
    private val logger: Logger,
    private val bleClient : BleClient,
    private val openBlueToothRule : OpenBlueToothRule,
): SearchBLEDeviceRule {
    private val tag = "SearchBLEDeviceRule"
    private var startTime by Delegates.notNull<Long>()

    @SuppressLint("MissingPermission")
    override fun invoke(
        serviceUUId: UUID,
    ): Flow<BleScannerStateDomainEntity> = callbackFlow {
        openBlueToothRule()
            .onSuccess {
                logger.logd(tag, "openBlueToothRule:onSuccess")
                this.launch {
                    bleClient
                        .startScan(scanMode = ScanSettings.SCAN_MODE_LOW_LATENCY)
                        .flowOn(Dispatchers.IO)
                        .distinctUntilChanged()
                        .catch {
                            // todo handle scan  error
                            // todo refresh on  java.lang.IllegalStateException: BT Adapter is not turned ON
                            send(BleScannerStateDomainEntity.Error(it))
                            logger.loge(tag, it)
                        }
                        .onCompletion {
                            val endTime = System.currentTimeMillis() - startTime
                            logger.logd(tag, "completed with $it ${endTime.toSeconds()}")
                            send(BleScannerStateDomainEntity.ScanEnd)
                        }
                        .onStart {
                            startTime = System.currentTimeMillis()
                            logger.logd(tag, "started")
                            send(BleScannerStateDomainEntity.Started)
                        }
                        .filter { result ->
                            matchServiceId(result,serviceUUId)
                        }
                        .take(1)
                        .collect{ result ->
                            logger.logd(tag, "found $result")
                            send(BleScannerStateDomainEntity.DeviceFound(result.device.toDomainEntity()))
                        }
                }
            }
            .onFailure {
                logger.logd(tag, "openBlueToothRule:onFailure")
                // todo handle scan  error
                launch {
                    send(BleScannerStateDomainEntity.Error(Throwable()))
                }
            }
        awaitClose()
    }

    private fun matchServiceId(result: ScanResult, filterUUId: UUID):Boolean = run{
        runCatching {
           result.scanRecord?.serviceUuids?.indexOf(ParcelUuid(filterUUId))?.let {index->
               index!=-1
           }?:false
        }.onSuccess {
            return@matchServiceId it
        }
        false
    }


}




