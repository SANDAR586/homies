package com.ydnsa.wificonnectapp.presentation.host

data class HostState(
    val isDiscovering: Boolean = false,
    val isConnected: Boolean = false,
    val messages: List<String> = emptyList()
                    )