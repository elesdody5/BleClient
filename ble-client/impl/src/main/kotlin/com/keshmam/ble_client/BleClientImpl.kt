package com.keshmam.ble_client

import android.bluetooth.BluetoothDevice
import com.keshmam.ble_client.api.BleClient
import com.keshmam.ble_client.api.BleGattConnector
import com.keshmam.ble_client.api.BleScanner
import com.keshmam.ble_client.scanner.BleScannerImpl
import kotlinx.coroutines.flow.Flow
import java.util.*
import  android.bluetooth.le.ScanResult
import com.keshmam.ble_client.api.BleManager
import com.keshmam.ble_client.api.entities.error.BleErrors

internal class BleClientImpl(
    private val bleManager: BleManager ,
    private val bleScanner: BleScanner,
    private val bleGattConnector: BleGattConnector,
): BleClient {

    /**
     * invoke [BleScannerImpl.startScan]
     * @see BleScannerImpl.startScan
     * @see BleScannerImpl.scanSettings
     * @see BleScannerImpl.scanFilter
     * @return flow<[ScanResult]>
     */
    // todo handle timeout
    override fun startScan(
        scanMode: Int,
    )= bleScanner.startScan(scanMode)


    override fun connect(device: BluetoothDevice): Flow<String> {
        TODO("Not yet implemented")
    }

    /**
     * switch device bluetooth on
     * @return  boolean bluetooth has been swtiched on
     * @throws BleErrors.MissingBluetoothPermissionToSwitchBluetoothError
     * @throws BleErrors.NoBlueToothAdapter
     */
    override fun enableBluetooth(){
        bleManager.enableBluetooth()
    }


}


