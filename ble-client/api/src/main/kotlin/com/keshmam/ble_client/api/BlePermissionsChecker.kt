package com.keshmam.ble_client.api


/**
 * use in [dagger.hilt.android.scopes.ActivityScoped]
 */
interface BlePermissionsChecker {

    /**
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
    fun hasBluetoothPermissionToScan() : Boolean

    /**
     * check system permissions to switch device bluetooth on/off
     */
    fun hasBluetoothPermissionToSwitchBluetooth(): Boolean


    /**
     * get all permissions required by [BleClient]
     */
    fun getAllBluetoothPermissions(): List<String>
}
