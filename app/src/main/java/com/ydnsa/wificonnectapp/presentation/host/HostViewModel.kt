package com.ydnsa.wificonnectapp.presentation.host


import android.content.Context
import android.net.wifi.WpsInfo
import android.net.wifi.p2p.WifiP2pConfig
import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import com.ydnsa.wificonnectapp.data.network.WifiRapper
import com.ydnsa.wificonnectapp.presentation.receivers.WifiDirectActionListener
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class HostViewModel(
    private val wifiRapper : WifiRapper
                   ) : ViewModel() {

    private val _state = MutableStateFlow(HostState())
    val state: StateFlow<HostState> = _state

    private val _effect = Channel<HostEffect>()
    val effect = _effect.receiveAsFlow()




    fun onIntent(intent: HostIntent) {
        when (intent) {
            is HostIntent.RequestConnection -> requestConnection(intent.device)
            is HostIntent.StopHosting -> stopHosting()
        }
    }

    private fun requestConnection( device : WifiP2pDevice) {
        val config = WifiP2pConfig().apply {
            deviceAddress = device.deviceAddress
            wps.setup = WpsInfo.PBC
        }
        wifiRapper.wifiP2pManager.connect(wifiRapper.channel , config , WifiDirectActionListener())

    }

    private fun stopHosting() {
        // Clean up
        _state.value = _state.value.copy(isDiscovering = false, isConnected = false)
    }

    // Add socket server setup here after connection is ready
}