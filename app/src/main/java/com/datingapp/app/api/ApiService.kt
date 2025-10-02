package com.datingapp.app.api

import com.datingapp.app.data.AppDefaultModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("appdefaults")
    suspend fun getAppDefaultResponse(): Response<AppDefaultModel>
}