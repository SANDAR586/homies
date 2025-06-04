package com.ydnsa.wificonnectapp

import android.app.Application
import com.ydnsa.wificonnectapp.di.appmodule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MyApp : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(listOf(appmodule))
        }
    }
}