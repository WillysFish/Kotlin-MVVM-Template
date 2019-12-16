package com.willy.kotlin_mvvm_template.base

import android.app.Application

/**
 * Created by Willy on 2019/11/5.
 */
object BaseApplication : Application(){

    lateinit var appVersionName:String

    override fun onCreate() {
        super.onCreate()
        appVersionName = packageManager.getPackageInfo(packageName,0).versionName
    }
}