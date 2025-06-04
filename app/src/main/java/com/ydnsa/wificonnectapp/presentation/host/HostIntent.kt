package com.ydnsa.wificonnectapp.presentation.host

import android.net.wifi.p2p.WifiP2pDevice

sealed class HostIntent {
    data class RequestConnection(val device : WifiP2pDevice) : HostIntent()
    object StopHosting : HostIntent()
    // You can add more intents like Disconnect, Retry, etc.
}