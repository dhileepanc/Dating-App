package com.datingapp.app.data.source


import com.datingapp.app.api.ApiService
import javax.inject.Inject

class SignupRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun signupUser(
        name: String?,
        email: String?,
        phone: String?,
        location: String?,
        gender: String?,
        dob: String?,
        height: String?,
        distance: String?,
        ageGroup: String?
    ): Boolean {
        // Replace with real API call, for demo returning true
        // Example: apiService.signupUser(SignupRequest(...))
        return true
    }
}
