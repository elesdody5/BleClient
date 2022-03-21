package com.keshmam.ble_client.manger

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.content.Context
import com.keshmam.ble_client.api.BleManager
import com.keshmam.ble_client.api.BlePermissionsChecker
import com.keshmam.ble_client.api.entities.error.BleErrors
import com.keshmam.ble_client.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext


/**
 * class hold instant of [BluetoothAdapter] , [BluetoothLeScanner]
 */
internal class BleManagerImpl(
    @ApplicationContext private val context: Context ,
    private val permissionsChecker: BlePermissionsChecker
) : BleManager {
    override val bluetoothAdapter: BluetoothAdapter? by lazy { context.getSystemService<BluetoothManager>()?.adapter }
    override val bleScanner: BluetoothLeScanner? by lazy { bluetoothAdapter?.bluetoothLeScanner }

    override fun isBluetoothEnabled() = bluetoothAdapter?.isEnabled?:false

    /**
     * switch device bluetooth on
     * @return  boolean bluetooth has been swtiched on
     * @throws BleErrors.MissingBluetoothPermissionToSwitchBluetoothError
     * @throws BleErrors.NoBlueToothAdapter
     */
    @SuppressLint("MissingPermission")
    override fun enableBluetooth() {
        if (permissionsChecker.hasBluetoothPermissionToScan()){
            bluetoothAdapter?.enable()
                ?:throw BleErrors.NoBlueToothAdapter
        }else throw  BleErrors.MissingBluetoothPermissionToSwitchBluetoothError
    }


}