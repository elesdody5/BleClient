package com.keshmam.di

import android.content.Context
import com.keshmam.ble_client.BleClientImpl
import com.keshmam.ble_client.api.*
import com.keshmam.ble_client.connector.BleGattConnectorImpl
import com.keshmam.ble_client.manger.BleManagerImpl
import com.keshmam.ble_client.scanner.BleScannerImpl
import com.keshmam.ble_client.util.permission.checker.BlePermissionsCheckerImpl
import com.keshmam.ble_client.util.permission.requester.AppCompatActivityBlePermissionsRequesterImpl
import com.keshmam.core.tracking.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BleSingletonModule {

    @Singleton
    @Provides
    fun provideBleManager(
      @ApplicationContext context: Context,
      permissionsChecker: BlePermissionsChecker
    ): BleManager {
        return BleManagerImpl(context,permissionsChecker)
    }


    @Singleton
    @Provides
    fun providePermissionsChecker(
      @ApplicationContext context: Context
    ): BlePermissionsChecker {
        return BlePermissionsCheckerImpl(context)
    }




}

@Module
@InstallIn(ActivityRetainedComponent::class)
class BleActivityRetainedModule {

    @ActivityRetainedScoped
    @Provides
    fun provideScanner(
        bleManager: BleManager,
        blePermissionsChecker : BlePermissionsChecker,
        logger: Logger
    ): BleScanner {
        return BleScannerImpl(
            bleManager = bleManager,
            blePermissionsChecker = blePermissionsChecker ,
            logger = logger
        )
    }

    @ActivityRetainedScoped
    @Provides
    fun provideConnector(): BleGattConnector {
        return BleGattConnectorImpl()
    }



    @ActivityRetainedScoped
    @Provides
    fun provideBleClient(
        bleManager: BleManager,
        bleScanner: BleScanner,
        bleGattConnector: BleGattConnector
    ): BleClient {
        return BleClientImpl(
            bleManager =  bleManager,
            bleScanner = bleScanner,
            bleGattConnector = bleGattConnector
        )
    }

}

@Module
@InstallIn(ActivityComponent::class)
class BleActivityModule {

    @ActivityScoped
    @Provides
    fun providePermissionRequester(
        @ActivityContext context: Context,
        permissionsChecker: BlePermissionsChecker
    ): AppCompatActivityBlePermissionsRequester {
        return AppCompatActivityBlePermissionsRequesterImpl(
            context = context,
            permissionsChecker = permissionsChecker
        )
    }

}