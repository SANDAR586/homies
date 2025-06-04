package com.ydnsa.wificonnectapp.di


import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.ydnsa.wificonnectapp.presentation.host.HostScreen

@Composable
    fun NavigationHost(navHostController : NavHostController){

        NavHost(
            navHostController, startDestination = "/host"
               ){
            composable ("/host"){
                HostScreen(navHostController=navHostController)
            }
        }
    }
