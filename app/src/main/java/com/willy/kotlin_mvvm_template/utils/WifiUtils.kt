package com.willy.kotlin_mvvm_template.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.SupplicantState
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import com.willy.kotlin_mvvm_template.base.BaseApplication


object WifiUtils {

    /**
     * 取得 WIFI SSID
     */
    fun getWifiSsid(mContext: Context): String {
        var ssid = ""
        val wifiManager = mContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo: WifiInfo

        wifiInfo = wifiManager.connectionInfo
        if (wifiInfo.supplicantState == SupplicantState.COMPLETED) {
            ssid = wifiInfo.ssid
            Log.i("wifiInfo.ssid = $ssid")
        }
        return ssid
    }


    @Suppress("DEPRECATION")
    class CameraWifiReceiver(private val camera: CameraWifiInterface) : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            Log.d("CameraWifiReceiver : action = " + intent.action)

            if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

//                val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
                val isWiFi: Boolean by lazy {
                    activeNetwork?.let {
                        it.type == ConnectivityManager.TYPE_WIFI
                    } ?: run {
                        false
                    }
                }
                Log.d("CameraWifiReceiver : isWifi = $isWiFi")

                if (isWiFi) {
                    if (getWifiSsid(context).contains("THETA")) {
                        // 啟動 Theta sdk
                        camera.startThetaSdk()
                        Log.d("BaseApplication.selectedCameraType = CameraType.THETA")
                    } else if (getWifiSsid(context).contains("ONE X")) {
                        // 啟動 Insta sdk
                        camera.startInstaSdk()
                        Log.d("BaseApplication.selectedCameraType = CameraType.INSTA")
                    }
                }

            }
        }
    }

    interface CameraWifiInterface {
        fun startInstaSdk()
        fun startThetaSdk()
    }
}