package com.ydnsa.wificonnectapp.util

import android.net.wifi.p2p.WifiP2pDevice

fun Int.getWifiP2PStatus(){
     when (this) {
        WifiP2pDevice.CONNECTED -> "Connected"
        WifiP2pDevice.INVITED -> "Invited"
        WifiP2pDevice.FAILED -> "Failed"
        WifiP2pDevice.AVAILABLE -> "Available"
        WifiP2pDevice.UNAVAILABLE -> "Unavailable"
        else -> "Unknown"
    }

}