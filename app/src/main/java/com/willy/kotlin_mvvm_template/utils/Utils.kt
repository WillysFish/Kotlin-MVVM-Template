package com.willy.kotlin_mvvm_template.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.net.wifi.SupplicantState
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.provider.Settings
import android.text.TextUtils

/**
 * Created by Willy on 2019/11/01.
 */
object Utils {

    /**
     * 開啟 APP 設定頁面
     */
    fun openSetting(mActivity: Activity) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", mActivity.packageName, null)
        intent.data = uri
        mActivity.startActivity(intent)
    }

    /**
     * Yes or No AlertDialog
     */
    fun askAlertShow(
        context: Context,
        strTitle: String = "",
        strBody: String,
        yesName: String,
        noName: String = "",
        btnCallback: (Boolean, DialogInterface, Int) -> Unit
    ) {
        val alert = AlertDialog.Builder(context)

        if (!TextUtils.isEmpty(strTitle)) {
            alert.setTitle(strTitle)
        }

        alert.setMessage(strBody)

        alert.setPositiveButton(yesName) { d, p -> btnCallback(true, d, p) }

        if (!TextUtils.isEmpty(noName)) {
            alert.setNegativeButton(noName) { d, p -> btnCallback(false, d, p) }
        }

        alert.show()
    }
}