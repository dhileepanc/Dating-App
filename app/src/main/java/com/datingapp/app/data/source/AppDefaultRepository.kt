package com.datingapp.app.data.source

import com.datingapp.app.api.ApiService
import com.datingapp.app.base.BaseApiResponse
import javax.inject.Inject

class AppDefaultRepository @Inject constructor(
    private val apiInterface: ApiService
) :
    BaseApiResponse() {

    companion object {
        val TAG = AppDefaultRepository::class.java.simpleName
    }

}