package com.keshmam.framework.ble

import com.keshmam.ble_client.api.BleClient
import com.keshmam.ble_client.api.entities.error.BleErrors
import com.keshmam.core.tracking.Logger
import com.keshmam.core.usecases.ble.OpenBlueToothRule

internal class OpenBlueToothRuleImpl(
    private val bleClient: BleClient,
    private val logger: Logger
):OpenBlueToothRule{

    private val tag = "OpenBlueToothRule"

    /**
     * switch device bluetooth on
     * @return  Result<[Nothing]?]
     *  result::onFailure :
     *  [BleErrors.MissingBluetoothPermissionToSwitchBluetoothError],
     *  [BleErrors.NoBlueToothAdapter]
     */
    override fun invoke(): Result<Nothing?> = runCatching {
        logger.logd(tag, "started")
        bleClient.enableBluetooth()
        null
    }.onSuccess {
        logger.logd(tag, "enabled")
    }.onFailure {
        logger.loge(tag, it)
    }

}
