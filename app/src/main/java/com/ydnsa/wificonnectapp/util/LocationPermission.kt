package com.ydnsa.wificonnectapp.util

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.*
import androidx.core.location.LocationManagerCompat.isLocationEnabled

@Composable
fun CheckLocationEnabled(
    context: Context = LocalContext.current
                        ) {
   val locationManager=remember { context.getSystemService(
       Context.LOCATION_SERVICE ) } as LocationManager

    var isLocationEnabled by remember {
        mutableStateOf(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                               locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }

    val locationSettingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
                                                                    ) {
        // Update the state when user returns from settings
        isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    LaunchedEffect(Unit) {
        if (!isLocationEnabled) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            locationSettingsLauncher.launch(intent)
        }
    }


}
