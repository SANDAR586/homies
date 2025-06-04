package com.ydnsa.wificonnectapp.presentation.host

sealed class HostEffect {
    data class ShowError(val message: String) : HostEffect()
}