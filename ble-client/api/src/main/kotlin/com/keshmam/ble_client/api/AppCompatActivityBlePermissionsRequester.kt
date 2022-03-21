package com.keshmam.ble_client.api

import com.keshmam.ble_client.api.entities.error.BleErrors


/**
 * used in [dagger.hilt.android.components.ActivityComponent]
 */
interface  AppCompatActivityBlePermissionsRequester{


    /**
     * check BLE feature availability at runtime
     * @throws BleErrors.BleNotSupportedError
     * @see [Check feature availability at runtime](https://developer.android.com/guide/topics/connectivity/bluetooth/permissions)
     */
    @Throws(BleErrors.BleNotSupportedError::class)
     fun checkBleAvailability():Boolean


    /**
     * request all permissions required by [BleClient]
     */
    fun grantAllPermissions(allGrantedBlock: () -> Unit)
}

