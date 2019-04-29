package com.quxianggif.core.util

import android.content.Context
import android.net.ConnectivityManager
import com.quxianggif.core.GifFun

/**
 * 判断设备当前网络状态的工具类
 * @author xuehj
 * @since 2019-04-23
 */
object NetworkUtil {
    const val NO_NETWORK = 0
    const val WIFI = 1
    const val MOBILE = 2
    const val UNKNOWN = -1

    /*fun checkNetWork(): Int {
        val manager = GifFun.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return when {
            wifi.isConnected -> WIFI
            mobile.isConnected -> MOBILE
            else -> NO_NETWORK
        }
    }*/

    fun checkNetwork(): Any {
        val manager = GifFun.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return when {
            wifi.isConnected -> WIFI
            mobile.isConnected -> MOBILE
            else -> NO_NETWORK
        }
    }


}