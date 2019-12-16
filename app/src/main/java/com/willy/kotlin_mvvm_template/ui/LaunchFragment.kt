package com.willy.kotlin_mvvm_template.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.willy.kotlin_mvvm_template.R
import com.willy.kotlin_mvvm_template.base.BaseActivity
import com.willy.kotlin_mvvm_template.base.BaseFragment
import com.willy.kotlin_mvvm_template.base.RequestCode
import com.willy.kotlin_mvvm_template.utils.Log
import com.willy.kotlin_mvvm_template.utils.PermissionUtil
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

/**
 * Created by Willy on 2019/10/31.
 */
class LaunchFragment : BaseFragment(), EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!PermissionUtil.hasAllRequiredPermission(requireActivity())) {
            // 詢問 Permissions in Runtime
            PermissionUtil.askAllRequiredPermission(requireActivity(), this)
        } else {
            // 有權限的話，隔一秒再轉頁
            Completable.fromAction {
                Log.i("Got all permissions which app needs.")
            }
                .delay(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                        goToLookingUpPage()
                    }
                })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            RequestCode.ASK_PERMISSIONS.code -> {
                // 不管是否有權限，以目前規則還是能使用 app
                goToLookingUpPage()

                // 先轉頁再處理 Permission 的處理
                EasyPermissions.onRequestPermissionsResult(
                    requestCode,
                    permissions,
                    grantResults,
                    this
                )
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun goToLookingUpPage() {
        (requireActivity() as BaseActivity).switchFragment(LaunchFragmentDirections.actionLaunchFragmentToLookingUpFragment())
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.d("Permissions Denied Count = " + perms.size)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d("Permissions Granted Count = " + perms.size)
    }

    override fun onRationaleAccepted(requestCode: Int) {
        Log.d("onRationaleAccepted")
    }

    override fun onRationaleDenied(requestCode: Int) {
        Log.d("onRationaleDenied")
        // 不管是否有權限，以目前規則還是能使用 app
        goToLookingUpPage()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LaunchFragment()
    }

}
