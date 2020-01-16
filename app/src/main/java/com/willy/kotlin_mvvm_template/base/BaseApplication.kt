package com.willy.kotlin_mvvm_template.base

import android.app.Application
import com.willy.kotlin_mvvm_template.utils.AESUtil

/**
 * Created by Willy on 2019/11/5.
 */
class BaseApplication : Application(){

    companion object {
        lateinit var appVersionName: String
    }

    override fun onCreate() {
        super.onCreate()
        // 初始 AES 加密的種子
        AESUtil.init(baseContext)
        // 取得 version info
        appVersionName = packageManager.getPackageInfo(packageName, 0).versionName
    }
}