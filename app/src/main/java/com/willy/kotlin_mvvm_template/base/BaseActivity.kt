package com.willy.kotlin_mvvm_template.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.willy.kotlin_mvvm_template.R
import com.willy.kotlin_mvvm_template.utils.Log
import com.willy.kotlin_mvvm_template.utils.SettingPrefs


/**
 * Created by Willy on 2019/10/30.
 */
open class BaseActivity : AppCompatActivity() {

    private lateinit var prefs: SettingPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SettingPrefs(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        Log.d("[%s] onResume()", javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Log.d("[%s] onPause()", javaClass.simpleName)
    }

    fun switchFragment(@IdRes actionIdRes: Int, bundle: Bundle = Bundle.EMPTY) {
        findNavController(R.id.container).navigate(actionIdRes, bundle)
    }

    fun switchFragment(directions: NavDirections) {
        findNavController(R.id.container).navigate(directions)
    }

    fun showShortToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun addTextToActionBar(title: String, subTitle: String = ""): BaseActivity {
        supportActionBar?.let { supportActionBar?.title = title }
        supportActionBar?.let { supportActionBar?.subtitle = subTitle }
        return this
    }

    fun setActionBarElevation(value: Float): BaseActivity {
        supportActionBar?.let { supportActionBar?.elevation = value }
        return this
    }


}