package com.willy.kotlin_mvvm_template.data.api

import android.os.NetworkOnMainThreadException
import androidx.annotation.StringRes
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.willy.kotlin_mvvm_template.R
import com.willy.kotlin_mvvm_template.utils.Log
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by WillyYan on 2019/06/10.
 * 後端 errorCode / 後端 errorMsg / memo 文字 for user
 * 之後遇到新的 error code 再一個個加進此 class
 */
enum class ErrorCode(val code: Int, val msg: String, @StringRes val memo: Int) {
    // Internet Error
    BAD_GATEWAY(502, "Bad Gateway", R.string.BAD_GATEWAY),
    NOT_FOUND(404, "Not Found 未找到請求資源", R.string.NOT_FOUND),
    CONNECTION_TIMEOUT(408, "Request Timeout 請求逾時", R.string.CONNECTION_TIMEOUT),
    NETWORK_NOT_CONNECT(499, "Network Not Connect", R.string.NETWORK_NOT_CONNECT),
    UNEXPECTED_ERROR(700, "Unexpected Error", R.string.UNEXPECTED_ERROR),
    UNKNOWN_HOST(111700, "UnknownHostException", R.string.UNKNOWN_HOST),


    // Programme Error
    JSON_PARSE_EXCEPTION(8001, "JsonParseException", R.string.JSON_PARSE_EXCEPTION),
    NETWORK_ON_MAIN_THREAD(8002, "NetworkOnMainThreadException", R.string.NETWORK_ON_MAIN_THREAD),
    SOCKET_TIMEOUT(8003, "SocketTimeoutException", R.string.SOCKET_TIMEOUT),


    // Error ( 因不同 response code 會有相同的 error code 所以加工一下來區分， 1024 -> 5001024 or 4001024 )
    PASSWORD_TOO_SHORT(5001001, "ACCOUNT_TOKEN_NOT_VERIFIED", R.string.alert_body_signup_passowrd_too_short),
    LOGIN_REQUIRED(4011002, "login required", R.string.LOGIN_REQUIRED),
    CANNOT_READ_PROPERTY_ID(5001003, "TypeError: Cannot read property 'id' of null", R.string.CANNOT_READ_PROPERTY_ID),
    ACCOUNT_TOKEN_NOT_VERIFIED(5001007, "ACCOUNT_TOKEN_NOT_VERIFIED", R.string.err_sign_not_verified),
    SEQUELIZE_UNIQUE(5001008, "signup failed\\nSequelizeUniqueConstraintError: Validation error", R.string.SEQUELIZE_UNIQUE),
    ACCOUNT_HAS_BEEN_USED(5001024, "ACCOUNT_HAS_BEEN_USED", R.string.alert_body_already_signed),
    ACCOUNT_ALREADY_EXISTED(4001024, "Account already existed.", R.string.ACCOUNT_ALREADY_EXISTED),
    ACCOUNT_EXPIRED(4001025, "Your account have expired. Please resubscribe to continue using the service.", R.string.error_1025),
    ACCOUNT_EXPIRED_401(4011025, "Your account have expired. Please resubscribe to continue using the service.", R.string.error_1025),
    INVALID_TOKEN(5001032, "Invalid token\nError: Wrong recipient, payload audience != requiredAudience", R.string.INVALID_TOKEN),
    UPLOAD_BUILDING_LIMIT(4002023, "INVALID_EMAIL_FORMAT", R.string.err_upload_building_limit),
    UPLOAD_PANORAMA_LIMIT(4002025, "INVALID_EMAIL_FORMAT", R.string.err_upload_panorama_limit),
    PAYLOAD_EXCEEDS_LIMIT(4006041, "API request payload exceeds limit", R.string.err_upload_panorama_limit),
    GOOGLE_SIGNIN_RESULT_FAIL(5009001, "GoogleSignInResult Fail", R.string.GOOGLE_SIGNIN_RESULT_FAIL),
    INVALID_EMAIL_FORMAT(50011125, "INVALID_EMAIL_FORMAT", R.string.err_sign_mail),


    UNKNOWN(-1, "Unknown", R.string.UNKNOWN),
    NO_ERROR(0, "No Error", R.string.NO_ERROR);


    companion object {

        private val map = values().associateBy(ErrorCode::code)

        @JvmStatic
        fun findErrorByThrowable(e: Throwable): ErrorCode {
            Log.e(e, "ErrorCode")
            if (e is UnknownHostException) return UNKNOWN_HOST
            if (e is JsonParseException) return JSON_PARSE_EXCEPTION
            if (e is NetworkOnMainThreadException) return NETWORK_ON_MAIN_THREAD
            if (e is SocketTimeoutException) return SOCKET_TIMEOUT

            if (e is HttpException) {
                var code = e.code()
                val bodyStr = e.response()?.errorBody()?.string()
                if (bodyStr != null) {
                    var erCode = Gson().fromJson(bodyStr, JsonObject::class.java).get("code").asInt
                    return map["$code$erCode".toInt()] ?: UNKNOWN
                }
            }

            return UNKNOWN
        }
    }
}