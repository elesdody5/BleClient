package com.keshmam.bleclient.main_activty.state_machine

import androidx.lifecycle.viewModelScope
import com.keshmam.bleclient.main_activty.vm.MainActivityViewModel
import kotlinx.coroutines.launch


fun MainActivityViewModel.reduce(
    event: MainUiEvent
)= viewModelScope.launch{
    when(event){
        is MainUiEvent.GetDevice ->{ handleSearchDeviceState(event)}
    }
}