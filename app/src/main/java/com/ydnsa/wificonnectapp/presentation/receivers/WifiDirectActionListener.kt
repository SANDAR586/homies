package com.ydnsa.wificonnectapp.presentation.receivers

import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import android.widget.Toast

class WifiDirectActionListener : WifiP2pManager.ActionListener
{
    override fun onSuccess()
    {
        Log.d("P2P", "Peer discovery started")
    }

    override fun onFailure(reason : Int)
    {
        val reason = when (reason) {
            WifiP2pManager.P2P_UNSUPPORTED -> "P2P unsupported"
            WifiP2pManager.BUSY -> "P2P busy"
            WifiP2pManager.ERROR -> "Internal error"
            else -> "Unknown error code $reason"
        }

        Log.e("P2P", "Discovery failed: $reason")

    }
}