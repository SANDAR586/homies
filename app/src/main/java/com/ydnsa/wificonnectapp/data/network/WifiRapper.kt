package com.ydnsa.wificonnectapp.data.network

import android.content.Context
import android.net.wifi.p2p.WifiP2pManager

class WifiRapper(context : Context)
{
    val wifiP2pManager = context.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
    val channel: WifiP2pManager.Channel? =
        wifiP2pManager.initialize(context, context.mainLooper, null)
}