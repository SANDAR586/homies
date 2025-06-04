package com.ydnsa.wificonnectapp.presentation.receivers

import android.net.wifi.p2p.WifiP2pDeviceList
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import com.ydnsa.wificonnectapp.data.network.AppState

class PeerListener : WifiP2pManager.PeerListListener
{
    override fun onPeersAvailable(peers : WifiP2pDeviceList?)
    {

      if(peers != null){
          if (peers.deviceList != null){
              val refreshPeers = peers.deviceList.toList()
              refreshPeers.forEach { it ->
                  Log.d("P2P", "Peer: ${it.deviceName} - ${it.deviceAddress}")
              }
              AppState.updateDevices(refreshPeers)
              if (refreshPeers.isEmpty()){
                 Log.d("Peers","Empty")
              }
          }
      }

    }
}