package com.keshmam.ble_client.api.entities.error


const val MissingBluetoothPermissionToScanMessage =  "bluetooth permissions to scan devices  not granted , " +
        "please ask user to grant required bluetooth permissions to scan" +
        "or use BlePermissionsUtil.grantAllPermissions"

const val MissingBluetoothPermissionToSwitchMessage =  "bluetooth permissions to switch devices bluetooth  not granted , " +
        "please ask user to grant required bluetooth permissions to scan" +
        "or use BlePermissionsUtil.grantAllPermissions"


const val UserDeniedRequestedPermissionsMessage =  "User Denied Requested Permissions"

const val NoBluetoothAdapterFoundMessage: String = "No bluetooth adapter found"

const val BleNotSupportedMessage: String = "BLE not supported with this device"

    