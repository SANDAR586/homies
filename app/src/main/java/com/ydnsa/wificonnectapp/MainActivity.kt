package com.ydnsa.wificonnectapp

import android.content.Context
import android.content.IntentFilter

import android.net.wifi.p2p.WifiP2pManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ydnsa.wificonnectapp.di.NavigationHost
import com.ydnsa.wificonnectapp.presentation.receivers.PeerListener
import com.ydnsa.wificonnectapp.presentation.receivers.WifiDirectActionListener
import com.ydnsa.wificonnectapp.presentation.receivers.WifiDirectBroadcastReceiver
import com.ydnsa.wificonnectapp.ui.theme.WifiConnectAppTheme
import android.Manifest


class MainActivity : ComponentActivity()
{

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        requestPermissions()
        enableEdgeToEdge()
        setContent {
            WifiConnectAppTheme {
                val navHostController: NavHostController  = rememberNavController()
                NavigationHost(navHostController)
            }
        }
    }

    override fun onResume()
    {
        super.onResume()

        val wifiP2pManager = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
       val  channel = wifiP2pManager.initialize(this, mainLooper, null)
         val intentFilter = IntentFilter().apply {
            addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
            addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
            addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)
            addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
        }
        val peerListListener = PeerListener()
        val receiver = WifiDirectBroadcastReceiver(wifiP2pManager,channel, peerListListener )
        registerReceiver(receiver,intentFilter)
        wifiP2pManager.discoverPeers(channel , WifiDirectActionListener())
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun requestPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
                                                                 ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {

                }

                permissions.getOrDefault(Manifest.permission.NEARBY_WIFI_DEVICES,false)->{

                }
                else -> {

                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.NEARBY_WIFI_DEVICES
                       )
                                                )
        }
    }

}
