package com.ydnsa.wificonnectapp.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.wifi.p2p.WifiP2pManager
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import  com.ydnsa.wificonnectapp.R

class MyWifiScannerService : Service()
{
    override fun onBind(intent : Intent?) : IBinder?
    {
       return null;
    }

    private lateinit var manager: WifiP2pManager
    private lateinit var channel: WifiP2pManager.Channel

    override fun onCreate()
    {
        super.onCreate()
        manager=getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
        channel = manager.initialize(this,mainLooper,null)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent : Intent? , flags : Int , startId : Int) : Int
    {
       startForeground(1 , createNotification("Finding Peers"))
        return START_STICKY
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(text : String) : Notification{
        val channelId="Wifi Direct"
        val channel = NotificationChannel(channelId, "Wi-Fi Direct", NotificationManager.IMPORTANCE_LOW)
        val manager2 = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager2.createNotificationChannel(channel)

        return Notification.Builder(this,channelId)
            .setContentTitle("Homies")
            .setContentTitle(text)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }
}