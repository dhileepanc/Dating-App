package com.datingapp.app.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class AppDefaultModel(
    @SerializedName("status")
    var status:String,
) : Parcelable {

}
