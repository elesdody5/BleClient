package com.keshmam.bleclient.main_activty

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.keshmam.ble_client.api.AppCompatActivityBlePermissionsRequester
import com.keshmam.bleclient.main_activty.vm.MainActivityViewModel
import com.keshmam.bleclient.R
import com.keshmam.bleclient.main_activty.state_machine.MainUiEvent
import com.keshmam.core.tracking.Logger
import com.keshmam.core.usecases.ble.RequestBlePermissionRule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val tag = "MainActivity"
    val SERVICE_UUID: UUID = UUID.fromString("deadf154-0000-0000-0000-0000deadf154")
    val CHARACTERISTIC_HEART_RATE_UUID: UUID = UUID.fromString("7db3e245-3608-41f3-a03c-955fcbd2ea4b")
    val CHARACTERISTIC_TEMPERATURE_UUID: UUID = UUID.fromString("7db3e246-3608-41f3-a03c-955fcbd2ea4b")

    val viewModel by viewModels<MainActivityViewModel>()

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var permissionsRequesterAppCompatActivity: AppCompatActivityBlePermissionsRequester

    @Inject
    lateinit var permissionRule: RequestBlePermissionRule<AppCompatActivityBlePermissionsRequester>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionRule(
            permissionsRequesterAppCompatActivity ,
            onPermissionsDenial = {
                // todo handle error
            }
        ){
            with(viewModel){
                uiState.asStateFlow()
                    .onStart {
                        sendEvent(MainUiEvent.GetDevice(SERVICE_UUID))
                    }
                    .onEach {
                        logger.logd(tag , "$it")
                    }
                    .flowWithLifecycle(lifecycle,Lifecycle.State.CREATED)
                    .launchIn(lifecycleScope)
            }
        }


    }





}