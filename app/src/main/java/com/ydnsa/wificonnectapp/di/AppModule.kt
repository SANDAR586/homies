package com.ydnsa.wificonnectapp.di


import android.content.Context
import android.net.wifi.p2p.WifiP2pManager
import androidx.lifecycle.*
import com.ydnsa.wificonnectapp.data.network.WifiRapper

import com.ydnsa.wificonnectapp.presentation.host.HostViewModel
import org.koin.android.ext.koin.*
import org.koin.core.module.dsl.*
import org.koin.dsl.*

val appmodule=module{

    single {
        WifiRapper(androidContext())
    }

    viewModel { HostViewModel(get<WifiRapper>()) }

}