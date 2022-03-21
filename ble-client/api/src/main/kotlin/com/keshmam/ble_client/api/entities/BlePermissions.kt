package com.keshmam.ble_client.api.entities

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

object BlePermissions{
    @RequiresApi(Build.VERSION_CODES.S)
    const val BLUETOOTH_CONNECT =Manifest.permission.BLUETOOTH_CONNECT
    @RequiresApi(Build.VERSION_CODES.S)
    const val BLUETOOTH_SCAN = Manifest.permission.BLUETOOTH_SCAN
    const val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    const val COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    const val BLUETOOTH =Manifest.permission.BLUETOOTH
    const val BLUETOOTH_ADMIN =Manifest.permission.BLUETOOTH_ADMIN
}
