package com.datingapp.app.di.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.*
import com.datingapp.app.base.BaseViewModel
import com.datingapp.app.base.MyApplication
import com.datingapp.app.data.source.SignupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: SignupRepository,
    application: MyApplication
) : BaseViewModel(application) {  // BaseViewModel must extend ViewModel or AndroidViewModel

    var name: String? = null
    var email: String? = null
    var phone: String? = null
    var location: String? = null
    var gender: String? = null
    var dob: String? = null
    var height: String? = null
    var distance: String? = null
    var ageGroup: String? = null

    private val _signupSuccess = MutableLiveData<Boolean>()
    val signupSuccess: LiveData<Boolean> get() = _signupSuccess

    fun printAllData() {
        Log.d("SignupViewModel", "Name: $name")
        Log.d("SignupViewModel", "Gender: $gender")
        Log.d("SignupViewModel", "Height: $height")
        Log.d("SignupViewModel", "Age Group: $ageGroup")
    }

    fun submitSignup() {
 /*       viewModelScope.launch {   // viewModelScope is here
            try {
                val result = repository.signupUser(
                    name, email, phone, location,
                    gender, dob, height, distance, ageGroup
                )
                _signupSuccess.value = result
            } catch (e: Exception) {
                _signupSuccess.value = false
            }
        }*/
    }
}
