package com.datingapp.app.di.viewmodel

import com.datingapp.app.base.BaseViewModel
import com.datingapp.app.base.MyApplication
import com.datingapp.app.data.source.AppDefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class AppDefaultViewModel @Inject constructor(
    private val appDefaultRepository: AppDefaultRepository,
    application: MyApplication
) : BaseViewModel(application) {

    val TAG = AppDefaultViewModel::class.java.simpleName
}