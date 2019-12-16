package com.willy.kotlin_mvvm_template.data.repositories

import com.google.gson.JsonObject
import com.willy.kotlin_mvvm_template.data.api.ApiRepoFactory
import com.willy.kotlin_mvvm_template.data.api.ApiResponseLiveData
import com.willy.kotlin_mvvm_template.data.api.service.VRMakerService
import com.willy.kotlin_mvvm_template.utils.Log
import io.reactivex.Single


/**
 * Created by WillyYan on 2019/06/10.
 * VRMaker 的 API Repository
 */
class VRMakerRepository(private val service: VRMakerService) {

    /**
     * 更新 Building 的 公開/未公開 狀態
     */
    fun changePublishForBuilding(
        buildId: String,
        isPublish: Boolean
    ): ApiResponseLiveData<JsonObject> {

        val json = JsonObject()
        json.addProperty("unavailable", !isPublish)

        Log.d("Start API: changePublishForBuilding")
        Log.json(json.toString())

        return ApiResponseLiveData(
            service.updateBuildingsV2(
                ApiRepoFactory.headerMap,
                buildId,
                json
            ).flatMap { jsonObject ->
                Log.d("End API: changePublishForBuilding")
                Log.json(jsonObject.toString())

                Single.just(jsonObject)
            })
    }

}

