package com.ydnsa.wificonnectapp.presentation.host

import android.content.Context
import android.net.wifi.WifiManager
import android.net.wifi.p2p.WifiP2pDevice
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel
import com.ydnsa.wificonnectapp.data.network.AppState
import com.ydnsa.wificonnectapp.util.getWifiP2PStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostScreen( viewModel: HostViewModel = koinViewModel() ,
               navHostController : NavHostController
               ) {
    val state by viewModel.state.collectAsState()
    val devices by AppState.deviceList.collectAsState()
    val isActive by AppState.isActive.collectAsState()
    val connected by AppState.groupFormed.collectAsState()
    val  hostState = remember{
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        if(connected){
            hostState.showSnackbar("Device is connected")
        }
    }


    fun isWifiEnabled(context: Context): Boolean {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        AppState.updateIsActive(wifiManager.isWifiEnabled)
        return wifiManager.isWifiEnabled
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                       Box(
                           modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                          ) {
                           Row(
                               modifier = Modifier.align(alignment = Alignment.CenterStart)
                              ) {
                               Text("Homies",

                                   )
                             if (isActive){
                                 Box(
                                     modifier = Modifier
                                         .size(5.dp)
                                         .background(Color.Green, shape = CircleShape)
                                    )
                             }
                           }
                           CircularProgressIndicator(
                               modifier = Modifier.size(20.dp)
                                   .align(alignment = Alignment.CenterEnd),
                               color = MaterialTheme.colorScheme.onBackground
                                                    )
                       }

                },
       //  modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
                     )
        },
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier ,
                hostState =hostState,
                        )
        },

        // when a group is formed , this should applear
        floatingActionButton = {
            ElevatedButton(onClick = {},
                           shape = CircleShape,
                           ) {
                Icon(Icons.Default.ChatBubbleOutline,
                     contentDescription = "Chats"
                     )
            }
        }
            ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),

              ) {
            Text(
                "Your phone is currently visiablel to near by devices."
                )
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                "Both devices must be using the same application and connected to the same network."
                )
            Spacer(modifier = Modifier.height(16.dp))
                Text("Available Devices")

            LazyColumn {
                items(devices){device ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 5.dp),
                        onClick = {
                            viewModel.onIntent(HostIntent.RequestConnection(device))
                        }
                        ) {
                        Text("Device ${device.deviceName}",
                             modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 5.dp,
                                                                        start = 16.dp, end = 16.dp),
                            )
                        Text(if(WifiP2pDevice.AVAILABLE==device.status) "tap to connect"
                             else " ${device.status.getWifiP2PStatus()}"
                             ,
                             modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 16.dp,
                                                                        start = 16.dp, end = 16.dp
                                                                        ),
                            )
                        if(WifiP2pDevice.CONNECTED==device.status){
                            ElevatedButton(onClick = {
//                                val wifiManager = context
                            }) {
                                Text("Disconnect")
                            }
                        }

                    }
                }
            }
        }

    }
}