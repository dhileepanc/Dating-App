package com.datingapp.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.datingapp.app.base.BaseActivity
import com.datingapp.app.data.AppDefaultModel
import com.datingapp.app.databinding.ActivityMainBinding
import com.datingapp.app.di.viewmodel.AppDefaultViewModel
import com.datingapp.app.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {



    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        redirection()
    }

    private fun redirection() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun setupViews() {

    }

    override fun observeData() {

    }
}