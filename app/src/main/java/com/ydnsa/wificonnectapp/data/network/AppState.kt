package com.ydnsa.wificonnectapp.data.network

import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pDeviceList
import kotlinx.coroutines.flow.MutableStateFlow

object AppState
{
    private val _shareState = MutableStateFlow<List<WifiP2pDevice>>(emptyList())
    val deviceList=_shareState

    fun updateDevices(devices: List<WifiP2pDevice>){
        _shareState.value=devices
    }

}