package com.keshmam.bleclient.main_activty.state_machine

import com.keshmam.core.entities.ble.BleScannerStateDomainEntity

data class MainUiState(
    val scannerState: BleScannerStateDomainEntity = BleScannerStateDomainEntity.Started,
)