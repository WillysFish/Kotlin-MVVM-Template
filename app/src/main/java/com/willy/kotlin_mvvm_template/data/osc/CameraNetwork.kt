package com.willy.kotlin_mvvm_template.data.osc

import android.app.Application
import android.text.TextUtils
import com.alibaba.fastjson.JSONObject
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadLargeFileListener
import com.liulishuo.filedownloader.FileDownloader
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class CameraNetwork private constructor(private val context: Application){
//    BaseCameraController.IPreviewStatusChangedListener,
//    BaseCameraController.ICaptureStatusChangedListener {

//    private var cameraCallback: CameraCallback? = null
//    private var mCameraStatus: BaseCamera.CameraStatus = BaseCamera.CameraStatus.IDLE

    private lateinit var pathArray: Array<String>
//    private var mWork: IWork? = null

    companion object {

        // For Singleton instantiation
        @Volatile
        private var network: CameraNetwork? = null

        fun getInstance(context: Application) =
            network ?: synchronized(this) {
                network ?: CameraNetwork(context).also { network = it }
            }

    }
//
//    fun getCameraStatus(): BaseCamera.CameraStatus {
//        return mCameraStatus
//    }
//
//    fun setCameraStatus(cameraStatus: BaseCamera.CameraStatus) {
//        mCameraStatus = cameraStatus
//    }
//
//    fun registerStatusChangedListener(previewStatusCallback: CameraCallback?) {
//        this.cameraCallback = previewStatusCallback
//        SDKApi.getInstance().setPreviewStatusChangedListener(this)
//        SDKApi.getInstance().setCaptureStatusChangedListener(this)
//    }
//
//    fun unregisterStatusChangedListener() {
//        this.cameraCallback = null
//        SDKApi.getInstance().setPreviewStatusChangedListener(null)
//        SDKApi.getInstance().setCaptureStatusChangedListener(null)
//    }
//
//    fun apInit() {
//        Log.d("BaseCamera - CameraNetwork - apInit()")
//        //EventBus.getDefault().register(this)
//        //清缓存，以便Demo每次能都跑完整流程，不清也可以
//        //fullDelete(File(requireContext().getExternalFilesDir(null)?.absolutePath + FOLDER_INSTA_SDK))
//        SDKApi.getInstance().setApplicationContext(context.applicationContext)
//        FileDownloader.setup(context)
//        //这个目录会缓存一些生成的文件，包括中间文件和目标文件，比如生成的HDR缓存和文件
//        SDKApi.getInstance().setCacheFolder(FOLDER_INSTA_SDK)
//        val file = File(context.getExternalFilesDir(null)?.absolutePath + FOLDER_HDR_SOURCE)
//        if (!file.exists()) {
//            copyFilesFromAssets("hdr_source", file.absolutePath)
//        }
//    }
//
//    fun deInit() {
//        //EventBus.getDefault().unregister(this)
//    }
//
//    fun openCamera() {
//        if (SDKApi.getInstance() != null) {
//            SDKApi.getInstance().openCamera(SDKApi.ConnectBy.AP)
//        }
//    }
//
//    fun initPlayer(playerView: CapturePlayerView) = SDKApi.getInstance().initPlayer(playerView)
//
//    fun startPreview() = SDKApi.getInstance().startPreviewStream()
//
//    fun stopPreview() {
//        Log.d("BaseCamera - CameraNetwork - stopPreview()")
//        if (SDKApi.getInstance() != null) {
//            SDKApi.getInstance().closePreviewStream()
//        }
//    }
//
//    fun closeCamera() {
//        if (SDKApi.getInstance() != null) {
//            Log.d("BaseCamera - CameraNetwork - closeCamera()")
//            SDKApi.getInstance().closeCamera(SDKApi.ConnectBy.AP)
//        }
//    }
//
//    fun captureImage() {
//        Log.d("captureImage - OSC CameraNetwork - start capture thread")
//        Thread(Runnable {
//            val result0 = setHDRCaptureMode()
//            if (result0 != 0) {
//                return@Runnable
//            }
//            val id = startCapture()
//            // 每隔 0.5 秒跟camera query 一次結果, 如果有結果了, 就break for loop
//            // try 0.5 x 60 = 30 秒
//            for (i in 0..59) {
//                val queryResult = queryResult(id!!)
//                Log.d("OSC", "query " + i + " times, result is " + queryResult?.toString())
//                if (queryResult != null) {
//                    // show fileUrl, show localFile
//                    cameraCallback?.onCaptureResult(true)
//                    var paths = queryResult.getString("_fileGroup")
//                    if (TextUtils.isEmpty(paths)) {
//                        paths = queryResult.getString("fileUrl")
//                    }
//                    paths = paths.replace("\\[".toRegex(), "").replace("]".toRegex(), "")
//                    pathArray = paths.split(",").toTypedArray()
//                    for (index in pathArray.indices) {
//                        pathArray[index] = pathArray[index].replace("\"".toRegex(), "")
//                    }
//                    break
//                } else {
//                    try {
//                        Thread.sleep(500)
//                    } catch (exception: InterruptedException) {
//                        cameraCallback?.onCaptureResult(false)
//                        exception.printStackTrace()
//                    }
//
//                }
//            }
//        }).start()
//    }
//
//    /********************** Status Change Callback  ********************/
//
//    override fun onPreviewStatusChanged(curPreviewStatus: BaseCamera.PreviewStatus) {
//        Log.d("BaseCamera - CameraNetwork - onPreviewStatusChanged()")
//        cameraCallback?.onPreviewStatusChanged(curPreviewStatus)
//        when (curPreviewStatus) {
//            BaseCamera.PreviewStatus.OPENING -> {
//            }
//            BaseCamera.PreviewStatus.OPENED -> {
//                Log.d("CameraNetwork - BaseCamera.PreviewStatus - OPENED")
//            }
//            BaseCamera.PreviewStatus.IDLE -> {
//            }
//        }
//    }
//
//    override fun onCaptureStatusChanged(
//        captureType: BaseCamera.CaptureType,
//        curCaptureStatus: BaseCamera.CaptureStatus,
//        filePaths: Array<String>,
//        errorCode: Int?
//    ) {
//        if (captureType == BaseCamera.CaptureType.INTERVAL_SHOOTING) {
//            when (curCaptureStatus) {
//                BaseCamera.CaptureStatus.IDLE, BaseCamera.CaptureStatus.WORKING -> {
//                }
//                BaseCamera.CaptureStatus.STARTING, BaseCamera.CaptureStatus.STOPPING -> {
//                }
//            }
//        }
//    }
//
//    override fun onCaptureTimeChanged(captureTime: Long) {
//
//    }
//
//    private fun fullDelete(file: File) {
//        if (!file.exists()) {
//            return
//        }
//
//        if (file.isFile) {
//            file.delete()
//        } else {
//            val files = file.listFiles()
//
//            if (files != null)
//                for (f in files) {
//                    fullDelete(f)
//                }
//
//            file.delete()
//        }
//    }
//
//    private fun copyFilesFromAssets(assetsPath: String, savePath: String) {
//        try {
//            val fileNames = context.assets!!.list(assetsPath)// 获取assets目录下的所有文件及目录名
//            if (fileNames!!.isNotEmpty()) {// 如果是目录
//                val file = File(savePath)
//                file.mkdirs()// 如果文件夹不存在，则递归
//                for (fileName in fileNames) {
//                    copyFilesFromAssets("$assetsPath/$fileName", "$savePath/$fileName")
//                }
//            } else {// 如果是文件
//                val inputStream = context.assets!!.open(assetsPath)
//                val fos = FileOutputStream(File(savePath))
//                val buffer = ByteArray(1024)
//                val byteCount = inputStream.read(buffer)
//                while (byteCount != -1) {// 循环从输入流读取
//                    // buffer字节
//                    fos.write(buffer, 0, byteCount)// 将读取的输入流写入到输出流
//                }
//                fos.flush()// 刷新缓冲区
//                inputStream.close()
//                fos.close()
//            }
//        } catch (e: Exception) {
//            // TODO Auto-generated catch block
//            e.printStackTrace()
//        }
//
//    }
//
//    private fun setHDRCaptureMode(): Int {
//        val client = OkHttpClient()
//
//        val mediaType = MediaType.parse("application/json")
//        val body = RequestBody.create(
//            mediaType, "{\n" +
//                    "\t\"name\":\"camera.setOptions\",\n" +
//                    "\t\"parameters\":{\n" +
//                    "\t\t\"options\":{\n" +
//                    "\t\t\t\"captureMode\": \"image\",\n" +
//                    "\t\t\t\"hdr\": \"hdr\",\n" +
//                    "\t\t\t\"exposureBracket\":{\n" +
//                    "\t\t\t\t\"shots\": 3,\n" +
//                    "\t\t\t\t\"increment\": 2\n" +
//                    "\t\t\t}\n" +
//                    "\t\t}\n" +
//                    "\t}\n" +
//                    "}"
//        )
//        val request = Request.Builder()
//            .url(SDKApi.getInstance().cameraHttpPrefix + "/osc/commands/execute")
//            .post(body)
//            .addHeader("Content-Type", "application/json")
//            .addHeader("Accept", "application/json")
//            .addHeader("X-XSRF-Protected", "1")
//            .build()
//
//        try {
//            val response = client.newCall(request).execute()
//            if (response.code() == 200) {
//                val jsonString = response.body()!!.string()
//                val jsonObject = JSONObject.parseObject(jsonString)
//                if ("done" == jsonObject["state"]) {
//                    return 0
//                }
//            }
//        } catch (exception: IOException) {
//            exception.printStackTrace()
//        }
//
//        return -1
//    }
//
//    private fun startCapture(): String? {
//        val client = OkHttpClient()
//
//        val mediaType = MediaType.parse("application/json")
//        val body = RequestBody.create(mediaType, "{\"name\":\"camera.takePicture\"}")
//        val request = Request.Builder()
//            .url(SDKApi.getInstance().cameraHttpPrefix + "/osc/commands/execute")
//            .post(body)
//            .addHeader("Content-Type", "application/json")
//            .addHeader("Accept", "application/json")
//            .addHeader("X-XSRF-Protected", "1")
//            .build()
//
//        try {
//            val response = client.newCall(request).execute()
//            if (response.code() == 200) {
//                val jsonString = response.body()!!.string()
//                val jsonObject = JSONObject.parseObject(jsonString)
//                return jsonObject.getString("id")
//            }
//        } catch (exception: IOException) {
//            exception.printStackTrace()
//        }
//
//        return null
//    }
//
//    private fun queryResult(id: String): JSONObject? {
//        val client = OkHttpClient()
//
//        val mediaType = MediaType.parse("application/json")
//        val body = RequestBody.create(
//            mediaType, "{\n" +
//                    "\t\"id\": \"" + id + "\"\n" +
//                    "}"
//        )
//        val request = Request.Builder()
//            .url(SDKApi.getInstance().cameraHttpPrefix + "/osc/commands/status")
//            .post(body)
//            .addHeader("Content-Type", "application/json")
//            .addHeader("Accept", "application/json")
//            .addHeader("X-XSRF-Protected", "1")
//            .build()
//
//        try {
//            val response = client.newCall(request).execute()
//            if (response.code() == 200) {
//                val jsonString = response.body()?.string()
//                val jsonObject = JSONObject.parseObject(jsonString)
//                if ("done" == jsonObject["state"]) {
//                    return jsonObject.getJSONObject("results")
//                }
//            }
//        } catch (exception: IOException) {
//            exception.printStackTrace()
//        }
//
//        return null
//    }
//
//    private fun getFileName(remotePath: String): String? {
//        return remotePath.substring(remotePath.lastIndexOf("/") + 1)
//    }
//
//    fun downloadImage() {
//        val folder: String = context.getExternalFilesDir(null)?.absolutePath + FOLDER_INSTA_DOWNLOAD
//        //FileUtils.fullDelete(File(folder))
//        val remotePaths: Array<String> = pathArray
//        val localPaths = arrayOfNulls<String>(remotePaths.size)
//        val completeCount = intArrayOf(0)
//        for (i in remotePaths.indices) {
//            localPaths[i] = folder + getFileName(remotePaths[i])
//            Log.rd("CameraNetwork", "localPaths[" + i + "] = " + localPaths[i])
//        }
//        for (i in remotePaths.indices) {
//            downloadFile(remotePaths[i], localPaths[i]!!, object : IDownloadResultListener {
//                override fun onComplete() {
//                    Log.rd("CameraNetwork", "onComplete() - downloadFile ")
//                    completeCount[0]++
//                    if (completeCount[0] == remotePaths.size) {
//                        mWork = SDKApi.getInstance().getWorkByUrl(localPaths)
//                        if (mWork!!.isHDRPhoto) {
//                            val outputPath: String =
//                                context.getExternalFilesDir(null)?.absolutePath + FOLDER_INSTA_SDK + "hdr_osc.jpg"
//                            SDKApi.getInstance()
//                                .generate(mWork, SDKApi.HDRType.HDR, false, outputPath,
//                                    object : SDKApi.IGenerateResult {
//                                        override fun onSuccess(hdrResult: HdrResult?) {
//                                            Log.rd(
//                                                "CameraNetwork",
//                                                "generate - onSuccess, hdrResult.type ="
//                                            )
//                                            exportCaptureImage()
//                                        }
//
//                                        override fun onFail(errorCode: Int) {
//                                            Log.rd("CameraNetwork", "generate - onFail")
//                                        }
//                                    })
//                        } else {
//                            Log.rd("CameraNetwork", "mWork - is not isHDRPhoto")
//                            exportCaptureImage()
//                        }
//                    }
//                }
//
//                override fun onError() {}
//            })
//        }
//    }
//
//    fun exportCaptureImage() {
//        Log.rd("CameraNetwork", "exportCaptureImage()")
//        val operationResult: IOperationResult = object : IOperationResult {
//            override fun onSuccess() {}
//            override fun onFail(errorCode: Int) {}
//            override fun onProgress(progress: Float) {}
//        }
//        if (mWork!!.isVideo) {
//            SDKApi.getInstance().exportVideo(
//                mWork,
//                FileUtils.createDirectoryIfNeeded(context.getExternalFilesDir(null)?.absolutePath + FOLDER_PLAY_EXPORT) + "video.mp4",
//                SDKApi.ExportMode.PANORAMA,
//                3840,
//                1920,
//                20 * 1024 * 1024,
//                mWork!!.fps.toInt(),
//                operationResult
//            )
//        } else {
//            val timeStamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(ZoneOffset.UTC)
//                .format(Instant.now())
//            SDKApi.getInstance().exportImage(
//                mWork,
//                FileUtils.createDirectoryIfNeeded(context.getExternalFilesDir(null)?.absolutePath + FOLDER_PLAY_EXPORT) + "image_" + timeStamp + ".jpg",
//                SDKApi.ExportMode.PANORAMA, 3840, 1920, 0.0, 0.0, 0.0, 0.0, operationResult
//            )
//        }
//    }
//
//    private fun downloadFile(
//        remotePath: String,
//        localPath: String,
//        downloadResultListener: IDownloadResultListener
//    ) {
//        FileDownloader.getImpl().create(remotePath)
//            .setPath(localPath)
//            .setForceReDownload(true)
//            .setAutoRetryTimes(3)
//            .setListener(object : FileDownloadLargeFileListener() {
//                override fun pending(
//                    task: BaseDownloadTask,
//                    soFarBytes: Long,
//                    totalBytes: Long
//                ) {
//                }
//
//                override fun connected(
//                    task: BaseDownloadTask,
//                    etag: String,
//                    isContinue: Boolean,
//                    soFarBytes: Long,
//                    totalBytes: Long
//                ) {
//                }
//
//                override fun progress(
//                    task: BaseDownloadTask,
//                    soFarBytes: Long,
//                    totalBytes: Long
//                ) {
//                }
//
//                override fun blockComplete(task: BaseDownloadTask) {}
//                override fun retry(
//                    task: BaseDownloadTask,
//                    ex: Throwable,
//                    retryingTimes: Int,
//                    soFarBytes: Long
//                ) {
//                }
//
//                override fun completed(task: BaseDownloadTask) {
//                    downloadResultListener.onComplete()
//                }
//
//                override fun paused(
//                    task: BaseDownloadTask,
//                    soFarBytes: Long,
//                    totalBytes: Long
//                ) {
//                }
//
//                override fun error(task: BaseDownloadTask, e: Throwable) {
//                    downloadResultListener.onError()
//                }
//
//                override fun warn(task: BaseDownloadTask) {}
//            }).start()
//    }
//
//    private interface IDownloadResultListener {
//        fun onComplete()
//        fun onError()
//    }
//
}