package com.willy.kotlin_mvvm_template.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.willy.kotlin_mvvm_template.R
import com.willy.kotlin_mvvm_template.base.BaseActivity
import com.willy.kotlin_mvvm_template.base.BaseFragment
import com.willy.kotlin_mvvm_template.utils.InjectorUtils
import com.willy.kotlin_mvvm_template.utils.Log
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_looking_up.*
import java.util.concurrent.TimeUnit

class LookingUpFragment : BaseFragment() {

    companion object {

        fun newInstance() = LookingUpFragment()
    }

    private val lookingUpViewModel: LookingUpViewModel by viewModels {
        InjectorUtils.provideLookingUpViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_looking_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        if (!EventBus.getDefault().isRegistered(this))
//            EventBus.getDefault().register(this)
        lookingUpViewModel.apInit()
        timerHandler.post(timerRunnable)
    }

//    private fun goToStartCreatePage() {
//        (requireActivity() as BaseActivity).switchFragment(LookingUpFragmentDirections.actionLookingUpFragmentToStartCreateFragment())
//    }

    private fun showLookUpCameraConnectionSuccess() {
        Log.d("BaseCamera - showLookUpCameraConnectionSuccess()")
        anim_wifi_lookup.visibility = View.GONE
        anim_connecting_success.visibility = View.VISIBLE
        imageView_camera.setBackgroundResource(R.drawable.ic_camera_connected)
        // 隔3秒再轉頁
        Completable.fromAction {
            Log.d("Got all permissions which app needs.")
        }
            .delay(3000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
//                    goToStartCreatePage()
                }
            })
    }

    private fun showLookingUpCameraConnection() {
        Log.d("BaseCamera - showLookingUpCameraConnection()")
        anim_connecting_success.visibility = View.GONE
        anim_wifi_lookup.visibility = View.VISIBLE
        imageView_camera.setBackgroundResource(R.drawable.ic_camera_unconnected)
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onCameraStatusChangeEvent(event: CameraStatusChangedEvent) {
//        CameraNetwork.getInstance(activity!!.application)
//            .setCameraStatus(event.baseCamera.cameraStatus)
//        when (event.baseCamera.cameraStatus) {
//            BaseCamera.CameraStatus.IDLE -> {
//                Log.d("BaseCamera - LookingUpFragment - CameraStatus - case IDLE:")
//            }
//            BaseCamera.CameraStatus.READY -> {
//                Log.d("BaseCamera - LookingUpFragment - CameraStatus - case READY:")
//                timerHandler.removeCallbacks(timerRunnable)
//                showLookUpCameraConnectionSuccess()
//            }
//            BaseCamera.CameraStatus.OPENING -> {
//                Log.d("BaseCamera - LookingUpFragment - CameraStatus - case OPENING:")
//            }
//            BaseCamera.CameraStatus.SYNCING -> {
//                Log.d("BaseCamera - LookingUpFragment - CameraStatus - case SYNCING:")
//            }
//            BaseCamera.CameraStatus.ERROR -> {
//                Log.d(
//                    "BaseCamera - LookingUpFragment - CameraStatus - case ERROR: " +
//                            "currentCameraStatus = $CameraNetwork.currentCameraStatus"
//                )
//                if (event.baseCamera.cameraStatus == BaseCamera.CameraStatus.READY) {
//                    Log.d("BaseCamera - currentCameraStatus == BaseCamera.CameraStatus.READY")
//                }
//                showLookingUpCameraConnection()
//            }
//            BaseCamera.CameraStatus.CLOSING -> {
//                Log.d(
//                    "BaseCamera - LookingUpFragment - CameraStatus - case CLOSING: " +
//                            "currentCameraStatus = ${event.baseCamera.cameraStatus}"
//                )
//                showLookingUpCameraConnection()
//            }
//            else -> {
//            }
//        }
//    }

    override fun onStart() {
        super.onStart()
//        val cameraStatus = CameraNetwork.getInstance(activity!!.application).getCameraStatus()
//        Log.d("onStart , currentCameraStatus = $cameraStatus")
//        if (cameraStatus == BaseCamera.CameraStatus.READY) {
//            timerHandler.removeCallbacks(timerRunnable)
//            showLookUpCameraConnectionSuccess()
//        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("onDestroy()")
        timerHandler.removeCallbacks(timerRunnable)
//        EventBus.getDefault().unregister(this)
        lookingUpViewModel.closeCamera()
        super.onDestroy()
    }

    var timerHandler = Handler()

    private val timerRunnable = object : Runnable {
        override fun run() {
            // Repeat this the same runnable code block again another 2 seconds
            timerHandler.postDelayed(this, 3000)
            // Do something here on the main thread
//            val cameraStatus = CameraNetwork.getInstance(activity!!.application).getCameraStatus()
//            if ((cameraStatus == BaseCamera.CameraStatus.IDLE)
//                or (cameraStatus == BaseCamera.CameraStatus.ERROR)
//            ) {
//                Log.d("BaseCamera , LookingUpFragment - postDelayed() status = $cameraStatus")
//                lookingUpViewModel.openCamera()
//            }
        }
    }
}
