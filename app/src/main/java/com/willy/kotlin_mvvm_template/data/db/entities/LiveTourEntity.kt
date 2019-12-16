package com.willy.kotlin_mvvm_template.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonArray
import java.util.*

/**
 * Created by Willy on 2019/11/7.
 */
@Entity(tableName = "livetours")
data class LiveTourEntity(
    // 名稱
    var name: String = "",
    // 資料夾位置
    var folderPath: String = "",
    // 建立時間
    @PrimaryKey
    val createAt: Calendar = Calendar.getInstance(),
    // 更新時間
    var updateAt: Calendar = Calendar.getInstance(),
    // 是否已上傳
    var wasUploaded: Boolean = false,
    // insta 的下載 url
    val hdr1Urls: JsonArray = JsonArray(),
    val hdr2Urls: JsonArray = JsonArray(),
    val hdr3Urls: JsonArray = JsonArray(),
    // 合成完的 HDR Photo 位置
    val hdrPaths: JsonArray = JsonArray(),
    // 待合成 video 位置
    val videoPaths: JsonArray = JsonArray(),
    // 已合成 video 位置
    var processedVideoPath: String = ""
)