package com.willy.kotlin_mvvm_template.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.willy.kotlin_mvvm_template.utils.Log
import com.willy.kotlin_mvvm_template.utils.SettingPrefs

/**
 * Created by Willy on 2019/10/30.
 */
open class BaseFragment : Fragment() {

    lateinit var prefs: SettingPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SettingPrefs(requireContext())
    }

    override fun onResume() {
        super.onResume()
        Log.d("[%s] onResume()", javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Log.d("[%s] onPause()", javaClass.simpleName)
    }
}