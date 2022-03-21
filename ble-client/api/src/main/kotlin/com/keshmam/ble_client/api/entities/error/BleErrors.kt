package com.keshmam.ble_client.api.entities.error

import com.keshmam.core.entities.ImplError


@ImplError
sealed class BleErrors(
    override val message: String,
    override val cause: Throwable = RuntimeException(message)
) :Throwable(){



    // todo handle error code
    @ImplError
    class ScannerError(errorCode: Int) : BleErrors(
        message = "An error occurred while scanning with code($errorCode)"
    )

    @ImplError
    object NoBlueToothAdapter: BleErrors(
        message = NoBluetoothAdapterFoundMessage  ,
        cause = NullPointerException("no bluetooth adapter found ")
    )

    @ImplError
    object MissingBluetoothPermissionToScan: BleErrors(
        message = MissingBluetoothPermissionToScanMessage,
        cause = IllegalArgumentException(MissingBluetoothPermissionToScanMessage)
    )

    @ImplError
    object BleNotSupportedError : BleErrors(
    message = BleNotSupportedMessage,
    cause = RuntimeException(BleNotSupportedMessage)
    )

    @ImplError
    object MissingBluetoothPermissionToSwitchBluetoothError :BleErrors(
        message = MissingBluetoothPermissionToSwitchMessage,
        cause = IllegalArgumentException(MissingBluetoothPermissionToSwitchMessage)
    )

    @ImplError
    object UserDeniedRequestedPermissionsError : BleErrors(
        message = UserDeniedRequestedPermissionsMessage,
        cause = RuntimeException(UserDeniedRequestedPermissionsMessage)
    )


}

