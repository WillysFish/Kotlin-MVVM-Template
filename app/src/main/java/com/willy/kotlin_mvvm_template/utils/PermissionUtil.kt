package com.willy.kotlin_mvvm_template.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.willy.kotlin_mvvm_template.R
import com.willy.kotlin_mvvm_template.base.RequestCode
import pub.devrel.easypermissions.EasyPermissions

/**
 * Created by Willy on 2019/11/01.
 */
object PermissionUtil {

    fun hasPermission(mContext: Context, permission: String) =
        EasyPermissions.hasPermissions(mContext, permission)

    /**
     * 有無精準 Location 權限
     */
    fun hasLocationPermission(mContext: Context): Boolean =
        hasPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)

    /**
     * 有無取得 WIFI State 權限
     */
    fun hasWifiStatePermission(mContext: Context): Boolean =
        hasPermission(mContext, Manifest.permission.ACCESS_WIFI_STATE)

    /**
     * 有無外部空間 Read / Write 權限
     */
    fun hasReadWriteStoragePermission(mContext: Context): Boolean =
        hasPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                && hasPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)

//    /**
//     * 有無 Camera 權限
//     */
//    fun hasCameraPermission(mContext: Context): Boolean =
//        hasPermission(mContext, Manifest.permission.CAMERA)


    /**
     * 檢查全部所需權限
     */
    fun hasAllRequiredPermission(mContext: Context): Boolean =
        hasWifiStatePermission(mContext)
                && hasLocationPermission(mContext)
                && hasReadWriteStoragePermission(mContext)

    /**
     * 以 Activity or Fragment 要求全部所需權限
     */
    fun askAllRequiredPermission(mActivity: Activity, mFragment: Fragment? = null) {
        var permissions = arrayOf<String>()

        // 若無權限則加入要求 array
        if (!hasLocationPermission(mActivity))
            permissions = permissions.plus(Manifest.permission.ACCESS_FINE_LOCATION)

        if (!hasWifiStatePermission(mActivity))
            permissions = permissions.plus(Manifest.permission.ACCESS_WIFI_STATE)

        if (!hasReadWriteStoragePermission(mActivity)) {
            permissions = permissions.plus(Manifest.permission.READ_EXTERNAL_STORAGE)
            permissions = permissions.plus(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        Log.json(Gson().toJson(permissions))
        if (permissions.isNotEmpty())
            askPermissions(mActivity, permissions, mFragment)
    }

    /**
     * 以 Activity or Fragment 要求權限
     */
    private fun askPermissions(
        mActivity: Activity,
        permissions: Array<String>,
        mFragment: Fragment?
    ) {
        mFragment?.run {
            EasyPermissions.requestPermissions(
                this,
                this.getString(R.string.request_permission_title),
                RequestCode.ASK_PERMISSIONS.code,
                *permissions
            )
        } ?: run {
            EasyPermissions.requestPermissions(
                mActivity,
                mActivity.getString(R.string.request_permission_title),
                RequestCode.ASK_PERMISSIONS.code,
                *permissions
            )
        }
    }
}