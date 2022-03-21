package com.keshmam.ble_client.api;

import android.annotation.SuppressLint
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings;
import com.keshmam.ble_client.api.entities.error.BleErrors
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * use in [dagger.hilt.android.scopes.ActivityRetainedScoped]
 */
interface BleScanner{


    /**
     * @param scanMode
     * [ScanSettings.SCAN_MODE_BALANCED] = 1;
     *n this mode, Android scans for 2 sec and waits 3 seconds.
     *  This is the ‘compromise’ mode. Not sure how useful this is though
     *
     * [ScanSettings.SCAN_MODE_LOW_LATENCY] = 2;
     * is recommended if the app will only be scanning for a brief period of time,
     * typically to find a very specific type of device.
     *
     *  In this mode, Android scans continuously. Obviously this uses the most power,
     *  but it will also guarantee the best scanning results.
     *  So if you want to find a device very quickly you must use this mode. But don’t use it for long running scans.
     *
     *
     * [ScanSettings.SCAN_MODE_LOW_POWER] = 0;
     * is used for extremely long-duration scans, or for scans that take place in the background (with the user’s permission).
     * Note that the high latency nature of this low power scan mode may result in missed advertisement packets
     * if the device you’re scanning for has a high enough advertising interval
     * that it doesn’t overlap with the app’s scan frequency.
     *
     *  In this mode, Android scans for 0.5 sec and then pauses 4.5 sec. If you choose this mode,
     *  it may take relatively long before a device is found, depending on how often a device sends an advertisement packet.
     *  But the good thing of this mode is that is uses very low power so it is ideal for long scanning times.
     *
     * [ScanSettings.SCAN_MODE_OPPORTUNISTIC] = -1;
     *In this mode, you only get scan results if other apps are scanning! Basically,
     *  this means it is totally unpredictable when Android will find your device.
     *  It may even not find your device at all…Again, you probably never want to use this in your own apps.
     *  The Android stack uses this mode to ‘downgrade’ your scan if you scan too long. More about that later.
     *
     *  @see <a href="https://medium.com/@martijn.van.welie/making-android-ble-work-part-1-a736dcd53b02">making-android-ble-work-part-1-a736dcd53b0</a>
     * @return [ScanSettings]
     *
     */
    private infix fun scanSettings(scanMode:Int) =
        ScanSettings.Builder()
            .setScanMode(scanMode)
            .build()

    /**
     * @return List<[ScanFilter]>
     */
    private fun scanFilter() : List<ScanFilter> = listOf(
        ScanFilter.Builder()
            .build()
    )


    /**
     * Start [BleManager.bleScanner] to get available devices
     * @param scanMode a specified key  in [ScanSettings] for more details see  [scanSettings]
     * @return flow<[ScanResult]>
     * @throws BleErrors.MissingBluetoothPermissionToScan
     * @throws BleErrors.NoBlueToothAdapter
     */
    @Throws(BleErrors.BleNotSupportedError::class , BleErrors.NoBlueToothAdapter::class)
    @SuppressLint("MissingPermission")
    fun startScan(
        scanMode: Int,
    ): Flow<ScanResult>


}