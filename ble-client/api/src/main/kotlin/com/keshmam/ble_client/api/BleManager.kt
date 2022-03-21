package com.keshmam.ble_client.api

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.BluetoothLeScanner
import com.keshmam.ble_client.api.entities.error.BleErrors


/**
 * class hold instant of [BluetoothAdapter] , [BluetoothLeScanner]
 */
interface BleManager{
    val bluetoothAdapter : BluetoothAdapter?
    val bleScanner : BluetoothLeScanner?

    /**
     * switch device bluetooth on
     * @return  boolean bluetooth has been swtiched on
     * @throws BleErrors.MissingBluetoothPermissionToSwitchBluetoothError
     * @throws BleErrors.NoBlueToothAdapter
     */
    fun enableBluetooth()

    fun isBluetoothEnabled(): Boolean
}