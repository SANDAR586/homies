package com.ydnsa.wificonnectapp.presentation.receivers

import android.R.attr.action
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.NetworkInfo
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService

class WifiDirectBroadcastReceiver(
    private val manager: WifiP2pManager?,
   private val channel: WifiP2pManager.Channel?,
   private val peerListListener: WifiP2pManager.PeerListListener) : BroadcastReceiver( )
{

    override fun onReceive(context : Context , intent : Intent)
    {

        when (intent.action){

        WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION ->{
            val state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1)
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Toast.makeText(context, "Wi-Fi P2P is ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Wi-Fi P2P is OFF", Toast.LENGTH_SHORT).show()
            }
        }

        WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION -> {
            manager?.requestPeers(channel,peerListListener)
            Toast.makeText(context,"P2P peers changed",Toast.LENGTH_SHORT).show()
        }

        WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION -> {
            val networkInfo = intent.getParcelableExtra<NetworkInfo>(WifiP2pManager.EXTRA_NETWORK_INFO)

            if(networkInfo != null && networkInfo.isConnected){
                manager?.requestConnectionInfo(channel){info ->

                    if (info.groupFormed && info.groupOwnerAddress != null) {
                        Log.d("P2P", "Connected to: ${info.groupOwnerAddress.hostAddress}")
                        if (info.isGroupOwner) {
                            Log.d("P2P", "This device is the group owner.")
                        } else {
                            Log.d("P2P", "This device is a peer.")
                        }
                    } else {
                        Log.d("P2P", "Group not formed.")
                    }

                }
            }

            Toast.makeText(context, "Connection changed", Toast.LENGTH_SHORT).show()
        }

        WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION -> {


            Toast.makeText(context, "This device's Wi-Fi state changed", Toast.LENGTH_SHORT).show()
        }
    }
        Toast.makeText(context, "Broadcast received!", Toast.LENGTH_SHORT).show()
    }
}