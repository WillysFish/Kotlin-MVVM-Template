package com.willy.kotlin_mvvm_template.data.api

/**
 * Created by WillyYan on 2019/06/10.
 * API 的統一回傳格式，將 Data 與 Error 合併回傳
 */
class ApiResponse<T>(val rsData: T?, val error: ErrorCode) {
    constructor(rsData: T) : this(rsData, ErrorCode.NO_ERROR)
    constructor(error: ErrorCode) : this(null, error)
}
