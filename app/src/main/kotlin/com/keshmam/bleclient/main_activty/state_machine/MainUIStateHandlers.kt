package com.keshmam.bleclient.main_activty.state_machine

import androidx.lifecycle.viewModelScope
import com.keshmam.bleclient.main_activty.vm.MainActivityViewModel
import com.keshmam.core.entities.BusinessRulesHandler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach



@BusinessRulesHandler
fun MainActivityViewModel.handleSearchDeviceState(
    event: MainUiEvent.GetDevice
){
    val tag = "getSearchDeviceState"
    searchDeviceRule(event.serviceUUID)
    .onEach { scannerState ->
        logger.logd(tag , scannerState.toString())
        uiState.value = uiState.value.copy( scannerState =  scannerState)
    }
    .launchIn(viewModelScope)
}


