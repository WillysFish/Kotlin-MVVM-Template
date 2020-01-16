package com.willy.kotlin_mvvm_template.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson

/**
 * Created by Willy on 2019/10/30.
 */

class SettingPrefs(context: Context) {

    companion object {
        // Data Key
//        val DB_KEY_NAME_CUSTOM_CATEGORY = "custom_category"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences("db", MODE_PRIVATE)

    fun setObject(keyName: String, value: Any) = setString(keyName, Gson().toJson(value))

    inline fun <reified T>getObject(keyName: String):T = Gson().fromJson(getString(keyName),T::class.java)

    fun setString(keyName: String, value: String) {
        try {
            val encryptValue = AESUtil.encrypt(value)
            prefs.edit().putString(keyName, encryptValue).apply()
        } catch (e: Exception) {
            Log.e("Exception", e.toString())
        }

    }

    fun getString(keyName: String, default: String = ""): String {
        var uid = prefs.getString(keyName, default)?:default

        if (uid == default) return uid

        try {
            uid = AESUtil.decrypt(uid)
        } catch (e: Exception) {
            Log.e("Exception", e.toString())
        }

        return uid
    }

    fun setBoolean(keyName: String, value: Boolean) {
        prefs.edit().putBoolean(keyName, value).apply()
    }

    fun getBoolean(keyName: String, default: Boolean = false): Boolean {
        return prefs.getBoolean(keyName, default)
    }

    fun setInt(keyName: String, value: Int) {
        prefs.edit().putInt(keyName, value).apply()
    }

    fun getInt(keyName: String, default: Int = 0): Int {
        return prefs.getInt(keyName, default)
    }

    fun setLong(keyName: String, value: Long) {
        prefs.edit().putLong(keyName, value).apply()
    }

    fun getLong(keyName: String, default: Long = 0): Long {
        return prefs.getLong(keyName, default)
    }

    fun removeData(keyName: String) {
        prefs.edit().remove(keyName).apply()
    }
}
