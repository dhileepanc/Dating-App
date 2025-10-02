package com.datingapp.app.ui

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.datingapp.app.MainActivity
import com.datingapp.app.R
import com.datingapp.app.base.BaseActivity
import com.datingapp.app.databinding.ActivitySplashBinding
import com.datingapp.app.di.viewmodel.AppDefaultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val viewDefaultModel: AppDefaultViewModel by viewModels()

    override fun inflateBinding()=ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Play splash animation and wait until it finishes
        binding.lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                moveToHome()
            }

            override fun onAnimationCancel(animation: Animator) {
                moveToHome()
            }

            override fun onAnimationRepeat(animation: Animator) {}
        })
    }

    private fun moveToHome() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun setupViews() {

    }

    override fun observeData() {

    }
}