package com.keshmam.core.entities.ble

sealed class BleScannerStateDomainEntity{
    object Started:BleScannerStateDomainEntity()
    data class DeviceFound(val device:BleDevice):BleScannerStateDomainEntity()
    object ScanEnd : BleScannerStateDomainEntity()
    data class Error(val error :Throwable):BleScannerStateDomainEntity()
}