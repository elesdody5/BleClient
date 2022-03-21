package com.keshmam.framework.ble

import com.keshmam.ble_client.api.AppCompatActivityBlePermissionsRequester
import com.keshmam.core.tracking.Logger
import com.keshmam.core.usecases.ble.RequestBlePermissionRule

internal class RequestBlePermissionRuleImpl(
    private val logger: Logger
) : RequestBlePermissionRule<AppCompatActivityBlePermissionsRequester> {

    override fun invoke(
        appCompatActivityBlePermissionsRequester: AppCompatActivityBlePermissionsRequester,
        onPermissionsDenial: () -> Unit,
        onPermissionsGranted: () -> Unit
    ) {
        runCatching {
            appCompatActivityBlePermissionsRequester.grantAllPermissions(onPermissionsGranted)
        }.onFailure {
            onPermissionsDenial()
        }
    }
}