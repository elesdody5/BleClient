package com.keshmam.ble_client.util.permission.checker

import android.content.Context
import android.os.Build
import com.keshmam.ble_client.api.BlePermissionsChecker
import com.keshmam.ble_client.api.entities.BlePermissions
import com.keshmam.ble_client.hasPermission
import dagger.hilt.android.qualifiers.ApplicationContext

import  com.keshmam.ble_client.BleClientImpl

internal class BlePermissionsCheckerImpl(
    @ApplicationContext private val  context : Context,
): BlePermissionsChecker {

    /**
     * check system permission to scan bluetooth device
     *      | from API  | to API  | runtime permissions|
     *      |-----------|---------|----------------------
     *      | 18        | 22      | (No needed)        |
     *      | 23        | 28      | One of below: - android.permission.ACCESS_COARSE_LOCATION - android.permission.ACCESS_FINE_LOCATION |
     *      | 29        | 30      | - android.permission.ACCESS_FINE_LOCATION - android.permission.ACCESS_BACKGROUND_LOCATION*|
     *      | 31        | current | - android.permission.BLUETOOTH_SCAN - android.permission.ACCESS_FINE_LOCATION**|
     *      |           |         | * Needed if [scan is performed in background](https://developer.android.com/about/versions/10/privacy/changes#app-access-device-location)|
     *      |           |         | ** Only needed if you want to obtain user's location with BLE scanning (BLUETOOTH_SCAN is not using neverForLocation attribute in your App|
     *@see [ble_client_readme.md]
     */
    override fun hasBluetoothPermissionToScan (): Boolean = run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            context hasPermission BlePermissions.BLUETOOTH_SCAN
        }else{
            context hasPermission  BlePermissions.FINE_LOCATION ||
            context hasPermission  BlePermissions.COARSE_LOCATION
        }
    }


    /**
     * check system permissions to switch device bluetooth on/off
     */
    override fun hasBluetoothPermissionToSwitchBluetooth (): Boolean = run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            context hasPermission BlePermissions.BLUETOOTH_CONNECT
        }else{
            context hasPermission  BlePermissions.BLUETOOTH &&
            context hasPermission  BlePermissions.BLUETOOTH_ADMIN
        }
    }


    /**
     * request all permissions required by [BleClientImpl]
     */

    override fun getAllBluetoothPermissions()
    = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
        listOf(
            BlePermissions.BLUETOOTH_SCAN ,
            BlePermissions.BLUETOOTH_CONNECT
        )
    }else{
        listOf(
            BlePermissions.BLUETOOTH ,
            BlePermissions.BLUETOOTH_ADMIN ,
            BlePermissions.FINE_LOCATION ,
            BlePermissions.COARSE_LOCATION ,
        )
    }




}

