package com.datingapp.app.ui

import android.os.Bundle
import android.text.InputType
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.datingapp.app.R
import com.datingapp.app.base.BaseActivity
import com.datingapp.app.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private var isPhoneSelected = true

    override fun inflateBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setupViews() {
        setupCenterToggle()
        updateLoginUI() // Initialize UI for default selection
    }

    override fun observeData() {}

    private fun setupCenterToggle() {
        val iconLay = binding.iconLay
        val centerIcon = binding.centerIcon

        binding.tvPhone.setOnClickListener {
            if (!isPhoneSelected) {
                isPhoneSelected = true
                animateIcon(iconLay, centerIcon, R.drawable.ic_phone)
                updateLoginUI()
            }
        }

        binding.tvEmail.setOnClickListener {
            if (isPhoneSelected) {
                isPhoneSelected = false
                animateIcon(iconLay, centerIcon, R.drawable.ic_email)
                updateLoginUI()
            }
        }
    }

    private fun updateLoginUI() {
        if (isPhoneSelected) {
            binding.typeOfLoginText.text = "Login with your\nPhone"
            binding.tilEmail.hint = getString(R.string.enter_your_phone)
            binding.edtEmail.inputType = InputType.TYPE_CLASS_PHONE

        } else {
            binding.typeOfLoginText.text = "Login with your\nEmail"
            binding.tilEmail.hint = getString(R.string.enter_your_email)
            binding.edtEmail.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        }
        // Clear previous input
        binding.edtEmail.text?.clear()
        binding.edtPassword.text?.clear()
    }

    private fun animateIcon(iconLay: FrameLayout, icon: ImageView, drawableRes: Int) {
        iconLay.animate().scaleX(0.7f).scaleY(0.7f).setDuration(120).withEndAction {
            icon.setImageResource(drawableRes)
            iconLay.animate().scaleX(1f).scaleY(1f).setDuration(120).start()
        }.start()
    }
}
