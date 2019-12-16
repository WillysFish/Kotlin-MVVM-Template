package com.willy.kotlin_mvvm_template.data.api

import android.os.Build
import com.willy.kotlin_mvvm_template.data.api.service.VRMakerService
import com.willy.kotlin_mvvm_template.data.repositories.VRMakerRepository
import com.willy.kotlin_mvvm_template.BuildConfig
import com.willy.kotlin_mvvm_template.base.BaseApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by WillyYan on 2019/06/10.
 * 取得 API Repository 的單例
 */
object ApiRepoFactory {

    const val HEADER_KEY_TOKEN_TYPE = "willy-Token-Type"
    const val HEADER_KEY_TOKEN = "willy-Token"
    const val HEADER_KEY_USER_ID = "willy-User-Id"
    const val HEADER_KEY_MOBILE_VERSION = "willy-Mobile-Version"
    const val HEADER_KEY_MOBILE_OS = "willy-Mobile-OS"
    const val HEADER_KEY_APP_VERSION = "willy-App-Version"
    const val HEADER_KEY_MOBILE_MANUFACTURER = "willy-Mobile-Manufacturer"
    const val HEADER_KEY_MOBILE_MODEL = "willy-Mobile-Model"
    const val HEADER_KEY_CONTENT_TYPE = "Content-Type"

    const val HEADER_VALUE_IMAGE_JPEG = "image/jpeg"
    const val HEADER_VALUE_APP_OCTET_STREAM = "application/octet-stream"


    @JvmStatic
    val headerMap: HashMap<String, String> by lazy {
        val result = HashMap<String, String>()
        result[HEADER_KEY_APP_VERSION] = BaseApplication.appVersionName
        result[HEADER_KEY_MOBILE_OS] = "android"
        result[HEADER_KEY_MOBILE_VERSION] = Build.VERSION.RELEASE
        result[HEADER_KEY_MOBILE_MODEL] = Build.MODEL
        result[HEADER_KEY_MOBILE_MANUFACTURER] = Build.MANUFACTURER
        result
    }

    private const val timeOut = 30

    @JvmStatic
    val vrMakerRepository: VRMakerRepository by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .build()

        return@lazy VRMakerRepository(
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VRMakerService::class.java)
        )
    }
}