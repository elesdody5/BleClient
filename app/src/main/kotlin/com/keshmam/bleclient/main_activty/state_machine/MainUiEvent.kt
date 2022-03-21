package com.keshmam.bleclient.main_activty.state_machine


import java.util.*

sealed class MainUiEvent {
    class GetDevice(val serviceUUID: UUID):MainUiEvent()
}
