package com.keshmam.bleclient.main_activty.vm

import androidx.lifecycle.*
import com.keshmam.bleclient.main_activty.state_machine.MainUiEvent
import com.keshmam.bleclient.main_activty.state_machine.MainUiState
import com.keshmam.bleclient.main_activty.state_machine.reduce
import com.keshmam.core.tracking.Logger
import com.keshmam.core.usecases.ble.SearchBLEDeviceRule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject



@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val searchDeviceRule : SearchBLEDeviceRule,
    val logger: Logger,
):ViewModel() {
    val uiState : MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())

    fun sendEvent(event : MainUiEvent) = reduce(event)
}

