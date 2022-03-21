package com.keshmam.framework.di

import com.keshmam.ble_client.api.BleClient
import com.keshmam.ble_client.api.AppCompatActivityBlePermissionsRequester
import com.keshmam.core.tracking.Logger
import com.keshmam.core.usecases.ble.OpenBlueToothRule
import com.keshmam.core.usecases.ble.RequestBlePermissionRule
import com.keshmam.core.usecases.ble.SearchBLEDeviceRule
import com.keshmam.framework.ble.OpenBlueToothRuleImpl
import com.keshmam.framework.ble.RequestBlePermissionRuleImpl
import com.keshmam.framework.ble.SearchBLEDeviceRuleImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class BleUseCasesModule {

    @ActivityRetainedScoped
    @Provides
    fun provideConnectToBLEDevice(
        logger: Logger,
        bleClient: BleClient,
        openBlueToothRule : OpenBlueToothRule,
    ):SearchBLEDeviceRule{
        return SearchBLEDeviceRuleImpl(
            logger = logger ,
            bleClient = bleClient,
            openBlueToothRule = openBlueToothRule
        )
    }

    @ActivityRetainedScoped
    @Provides
    fun provideOpenBlueToothRule(
        logger: Logger,
        bleClient: BleClient
    ): OpenBlueToothRule {
        return OpenBlueToothRuleImpl(
            logger = logger ,
            bleClient = bleClient
        )
    }

    @ActivityRetainedScoped
    @Provides
    fun provideRequestBlePermissionRule(
        logger: Logger,
    ): RequestBlePermissionRule<AppCompatActivityBlePermissionsRequester>{
        return RequestBlePermissionRuleImpl(
            logger = logger ,
        )
    }
}