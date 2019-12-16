/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.willy.kotlin_mvvm_template.data.repositories

import com.willy.kotlin_mvvm_template.data.osc.CameraNetwork
import com.willy.kotlin_mvvm_template.utils.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CameraRepository private constructor(private val cameraNetwork: CameraNetwork):
    CameraCallback {

//    private var cameraListener: CameraListener? = null

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CameraRepository? = null

        fun getInstance(cameraNetwork: CameraNetwork) =
            instance ?: synchronized(this) {
                instance ?: CameraRepository(cameraNetwork).also { instance = it }
            }
    }

//    fun registerStatusChangedListener(cameraListener: CameraListener?) {
//        this.cameraListener = cameraListener
//        cameraNetwork.registerStatusChangedListener(this)
//    }
//
//    fun unregisterStatusChangedListener() {
//        this.cameraListener = null
//        cameraNetwork.unregisterStatusChangedListener()
//    }
//
//    suspend fun apInit() {
//        withContext(Dispatchers.IO) {
//            cameraNetwork.apInit()
//        }
//    }
//
//
//    suspend fun deInit() {
//        cameraNetwork.deInit()
//    }
//
//    suspend fun openCamera() {
//        withContext(Dispatchers.IO) {
//            Log.d("BaseCamera - cameraNetwork - openCamera()")
//            cameraNetwork.openCamera()
//        }
//    }
//
////    suspend fun openCamera(): BaseCamera.CameraStatus  = suspendCoroutine { continuation ->
////        cameraNetwork.openCamera(object : CameraStatusCallback {
////            override fun onCameraStatusEvent(cameraStatus: BaseCamera.CameraStatus) {
////                continuation.resume(cameraStatus)
////            }
////        })
////    }
//
//    suspend fun initPlayer(playerView: CapturePlayerView) {
//        withContext(Dispatchers.Main) {
//            Log.d("Dispatchers Main - CameraRepository - initPlayer()")
//            cameraNetwork.initPlayer(playerView)
//        }
//    }
//
//    suspend fun startPreview() {
//        withContext(Dispatchers.IO) {
//            cameraNetwork.startPreview()
//        }
//    }
//
//    suspend fun stopPreview() {
//        withContext(Dispatchers.IO) {
//            cameraNetwork.stopPreview()
//        }
//    }
//
//    suspend fun closeCamera() {
//        withContext(Dispatchers.IO) {
//            Log.d("BaseCamera - CameraRepository - closeCamera()")
//            cameraNetwork.closeCamera()
//        }
//        //unregisterStatusChangedListener()
//        //cameraNetwork.deInit()
//    }
//
//    suspend fun captureImage() {
//        withContext(Dispatchers.IO) {
//            cameraNetwork.captureImage()
//        }
//    }
//
//    suspend fun downloadImage() {
//        withContext(Dispatchers.IO) {
//            cameraNetwork.downloadImage()
//        }
//    }
//
//    override fun onPreviewStatusChanged(previewStatus: BaseCamera.PreviewStatus) {
//        Log.d("BaseCamera - CameraRepository - previewStatus = $previewStatus")
//        cameraListener?.onPreviewStatusChanged(previewStatus)
//    }
//
    override fun onCaptureResult(isSuccess: Boolean) {
//        cameraListener?.onCaptureResult(isSuccess)
    }
}

interface CameraCallback {
//    fun onPreviewStatusChanged(previewStatus: BaseCamera.PreviewStatus)
    fun onCaptureResult(isSuccess: Boolean)
}