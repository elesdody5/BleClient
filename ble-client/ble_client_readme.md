Project Overview 

Implementing Android BLE Receiver to demonstrate how we can create a separated module BLE client which will be implemented 
inside a Clean Architecture Project with Onion approach

This project created with : 
- Kotlin
- 

-What is BLE ?
Bluetooth Low Energy, a subset of the 2.4 GHz Bluetooth wireless technology that specializes
in low power and oftentimes infrequent data transmissions for connected devices.

BLE module Guide:
-Manifest Permissions : (Reference)["https://developer.android.com/guide/topics/connectivity/bluetooth/permissions"]

| from API  | to API  | Acceptable runtime permissions                                                                                                             |
|-----------|---------|--------------------------------------------------------------------------------------------------------------------------------------------|
| 18        | 22      | (No runtime permissions needed)                                                                                                            |
| 23        | 28      | One of below: - android.permission.ACCESS_COARSE_LOCATION - android.permission.ACCESS_FINE_LOCATION                                        |
| 29        | 30      | - android.permission.ACCESS_FINE_LOCATION - android.permission.ACCESS_BACKGROUND_LOCATION*                                                 |
| 31        | current | - android.permission.BLUETOOTH_SCAN - android.permission.ACCESS_FINE_LOCATION**                                                            |
|           |         | * Needed if [scan is performed in background](https://developer.android.com/about/versions/10/privacy/changes#app-access-device-location)  |
|           |         | ** Only needed if you want to obtain user's location with BLE scanning (BLUETOOTH_SCAN is not using neverForLocation attribute in your App |
```xml
    
    <!--If Bluetooth is a critical piece of your app--> 
    <uses-feature android:name="android.hardware.bluetooth_le" android:required="true"/>

    <!-- Request legacy Bluetooth permissions on older devices. -->
    <uses-permission android:name="android.permission.BLUETOOTH" android:maxSdkVersion="30" />
    <!--If you want your app to initiate device discovery or manipulate Bluetooth settings-->
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" android:maxSdkVersion="30" />
    <!-- permissions to scan devices from api 23 to 30 -->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" android:maxSdkVersion="30" />
    <!-- permissions to scan devices from api 23 to 28 -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" android:maxSdkVersion="30" />

    <!-- Needed only if your app looks for Bluetooth devices. -->
    <!-- Include "neverForLocation" only if you can strongly assert that
             your app never derives physical location from Bluetooth scan results. -->
    <uses-permission android:name="android.permission.BLUETOOTH_SCAN"
        android:usesPermissionFlags="neverForLocation"
        tools:targetApi="s" />


    <!-- Needed only if your app communicates with already-paired Bluetooth
         devices. -->
    <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />


```


  

BLE references :
-https://developer.android.com/guide/topics/connectivity/bluetooth/ble-overview
-https://punchthrough.com/android-ble-guide/
-https://medium.com/@martijn.van.welie/making-android-ble-work-part-1-a736dcd53b02




