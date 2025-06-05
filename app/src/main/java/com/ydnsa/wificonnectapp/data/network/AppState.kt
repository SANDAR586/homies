package com.ydnsa.wificonnectapp.data.network

import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pDeviceList
import kotlinx.coroutines.flow.MutableStateFlow

object AppState
{
    // peers list
    private val _shareState = MutableStateFlow<List<WifiP2pDevice>>(emptyList())
    val deviceList=_shareState

    fun updateDevices(devices: List<WifiP2pDevice>){
        _shareState.value=devices
    }

    // wifi_direct active
    private val _isActive= MutableStateFlow<Boolean>(false)
    val isActive=_isActive

    fun updateIsActive(activeStatus: Boolean){
        _isActive.value=activeStatus
    }

    // gorup owner
    private val _groupOwner = MutableStateFlow<Boolean>(false)
    val groupOwner=_groupOwner
    fun updateGroupOwner(status: Boolean){
        _groupOwner.value=status
    }

    // gorup owner
    private val _groupFormed= MutableStateFlow<Boolean>(false)
    val groupFormed=_groupFormed
    fun updateGroupFormed(status: Boolean){
        _groupFormed.value=status
    }

}