package com.willy.kotlin_mvvm_template.base

import android.app.Application

/**
 * Created by Willy on 2019/11/5.
 */
class BaseApplication : Application(){

    companion object {
        lateinit var appVersionName: String
    }

    override fun onCreate() {
        super.onCreate()
        // 取得 version info
        appVersionName = packageManager.getPackageInfo(packageName, 0).versionName
    }
}