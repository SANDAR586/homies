package com.ydnsa.wificonnectapp.presentation.host

import android.content.Context
import android.net.wifi.p2p.WifiP2pManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel
import com.ydnsa.wificonnectapp.R
import com.ydnsa.wificonnectapp.data.network.AppState
import kotlinx.coroutines.flow.toList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostScreen( viewModel: HostViewModel = koinViewModel() ,
               navHostController : NavHostController
               ) {
    val state by viewModel.state.collectAsState()
    val devices by AppState.deviceList.collectAsState()



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                       ) {
                        Text("Homies",
                             modifier = Modifier.align(alignment = Alignment.CenterStart)
                             )
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp)
                            .align(alignment = Alignment.CenterEnd),
                            color = MaterialTheme.colorScheme.onBackground
                                                 )
                    }
                },
         modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
                     )
        },


            ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
          //  verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
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
                        modifier = Modifier.fillMaxWidth().padding(7.dp),
                        onClick = {
                            viewModel.onIntent(HostIntent.RequestConnection(device))
                        }

                        ) {
                        Text("Device ${device.deviceName}",
                             modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 5.dp),
                            )
                        Text("tap to connect",
                             modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 16.dp),
                            )
                    }
                }
            }
        }

    }
}