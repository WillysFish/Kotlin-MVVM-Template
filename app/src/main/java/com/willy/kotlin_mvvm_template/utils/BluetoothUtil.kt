package com.willy.kotlin_mvvm_template.utils

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult

/**
 * Created by Willy on 2019/10/30.
 */
class BluetoothUtil {

    val REQUEST_ENABLE_BT = 800
    val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    companion object {
        @JvmStatic
        val INSTANCE: BluetoothUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            BluetoothUtil()
        }
    }

    /**
     * 要求開啟 Bluetooth
     */
    fun requestEnableBT(mActivity: Activity) {
        if (bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(mActivity, enableBtIntent, REQUEST_ENABLE_BT, null)
        }
    }

    /**
     * 要求可被 Bluetooth Devices 發現
     */
    fun requestPublishDevice(mActivity: Activity) {
        val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 60)
        }
        mActivity.startActivity(discoverableIntent)
    }

    /**
     * 取得曾經連線的 Bluetooth Devices
     */
    fun paredDevices(): Set<BluetoothDevice> = bluetoothAdapter.bondedDevices


    /**
     * 開始搜尋 Bluetooth Devices
     */
    fun startDiscoverDevices() {
        if (!bluetoothAdapter.isDiscovering)
            bluetoothAdapter.startDiscovery()
    }

    /**
     * 停止搜尋 Bluetooth Devices
     */
    fun stopDiscoverDevices() {
        if (bluetoothAdapter.isDiscovering)
            bluetoothAdapter.cancelDiscovery()
    }

}