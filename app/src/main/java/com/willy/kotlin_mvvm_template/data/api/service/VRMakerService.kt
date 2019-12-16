package com.willy.kotlin_mvvm_template.data.api.service

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.willy.kotlin_mvvm_template.data.api.ApiRepoFactory
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by WillyYan on 2019/06/10.
 * VRMaker 的 API Service
 */
interface VRMakerService {

    @POST("api/v2/buildings")
    fun createBuildingsV2(@HeaderMap headerMap: Map<String, String>,
                          @Body token: JsonObject): Single<JsonObject>

    @PUT("api/v2/buildings/{id}")
    fun updateBuildingsV2(@HeaderMap headerMap: Map<String, String>, @Path("id") id: String, @Body token: JsonObject): Single<JsonObject>

    @FormUrlEncoded
    @POST("api/v2/panoramas")
    fun createPanoramaV2(@HeaderMap headerMap: Map<String, String>, @Field("buildingId") buildingId: String, @Field("panoramas") panoramas: String): Single<JsonArray>

}
