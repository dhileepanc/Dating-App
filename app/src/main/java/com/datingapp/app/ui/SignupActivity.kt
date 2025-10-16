package com.datingapp.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.datingapp.app.base.BaseActivity
import com.datingapp.app.databinding.ActivitySignupBinding
import com.datingapp.app.di.viewmodel.SignupViewModel
import com.datingapp.app.ui.SignupPagerAdapter
import com.datingapp.app.ui.SignupStepAccountFragment
import com.datingapp.app.ui.SignupStepGenderFragment
import com.datingapp.app.ui.signup.SignupStepAgeGroupFragment
import com.datingapp.app.ui.signup.SignupStepDOBFragment
import com.datingapp.app.ui.signup.SignupStepDistanceFragment
import com.datingapp.app.ui.signup.SignupStepHeightFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : BaseActivity<ActivitySignupBinding>() {

    private lateinit var signupAdapter: SignupPagerAdapter
    private val signupViewModel: SignupViewModel by viewModels()

    override fun inflateBinding() = ActivitySignupBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
//        observeViewModel()
    }

    override fun setupViews() {}
    override fun observeData() {}

    private fun setupViewPager() {
        val fragments = listOf(
            SignupStepAccountFragment(),   // Name, email, location
            SignupStepGenderFragment(),    // Gender
            SignupStepDOBFragment(),       // DOB
            SignupStepHeightFragment(),    // Height
            SignupStepDistanceFragment(),  // Distance preference
            SignupStepAgeGroupFragment()   // Age group preference
        )

        signupAdapter = SignupPagerAdapter(this, fragments)
        binding.viewPagerSignup.adapter = signupAdapter
        binding.viewPagerSignup.isUserInputEnabled = false
    }

    fun nextStep() {
        val next = binding.viewPagerSignup.currentItem + 1
        if (next < signupAdapter.itemCount) {
            binding.viewPagerSignup.currentItem = next
        } else {
            signupViewModel.submitSignup() // Last step submit
        }
    }

    fun previousStep() {
        val prev = binding.viewPagerSignup.currentItem - 1
        if (prev >= 0) binding.viewPagerSignup.currentItem = prev
    }

    private fun observeViewModel() {
        signupViewModel.signupSuccess.observe(this) { success ->
            if (success) {
                // Signup success, navigate to main/home activity
                finish()
            } else {
                // Show error message
                showToast("Signup failed. Please try again")
            }
        }
    }
}
