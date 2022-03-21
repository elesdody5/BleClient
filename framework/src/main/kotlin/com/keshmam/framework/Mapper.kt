package com.keshmam.framework

import android.bluetooth.BluetoothDevice
import com.keshmam.core.entities.ble.BleDevice


fun BluetoothDevice.toDomainEntity(): BleDevice = BleDevice()
