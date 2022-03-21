package com.keshmam.ble_client.util.permission.requester

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.keshmam.ble_client.BleClientImpl
import com.keshmam.ble_client.activity
import com.keshmam.ble_client.api.BlePermissionsChecker
import com.keshmam.ble_client.api.AppCompatActivityBlePermissionsRequester
import com.keshmam.ble_client.api.entities.error.BleErrors
import com.keshmam.ble_client.missingSystemFeature
import dagger.hilt.android.qualifiers.ActivityContext


internal class AppCompatActivityBlePermissionsRequesterImpl(
    @ActivityContext private val  context : Context,
    private val permissionsChecker: BlePermissionsChecker
): AppCompatActivityBlePermissionsRequester {


    /**
     * check BLE feature availability at runtime
     * @see [Check feature availability at runtime](https://developer.android.com/guide/topics/connectivity/bluetooth/permissions)
     */
     override fun checkBleAvailability():Boolean{

       return with(context.activity() as AppCompatActivity){
            packageManager.takeIf {
                it.missingSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
            }?.run {
                false
            }
           true
       }
    }

    /**
     * request all permissions required by [BleClientImpl]
     */
    override fun grantAllPermissions(
        allGrantedBlock: ()->Unit ,
    ){
        with(context.activity() as AppCompatActivity){
            registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions -> permissions.entries.forEach {
                    permissionStatus-> if (!permissionStatus.value) {
                        throw BleErrors.UserDeniedRequestedPermissionsError
                    }
                }.also {
                    allGrantedBlock()
                }
            }.launch(permissionsChecker.getAllBluetoothPermissions().toTypedArray())
        }
    }


}

