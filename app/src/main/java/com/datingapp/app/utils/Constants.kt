package com.datingapp.app.utils

class Constants {

    companion object {
        val TAG = Constants::class.java.simpleName

        const val SITE_URL = "http://192.168.234.232:8000/"

        const val END_POINT ="api/"
        val API_URL = "$SITE_URL$END_POINT" //base url for all api

        public const val TAG_USER_ID = "user_id"
        public const val TAG_USER_NAME = "user_name"

    }
}