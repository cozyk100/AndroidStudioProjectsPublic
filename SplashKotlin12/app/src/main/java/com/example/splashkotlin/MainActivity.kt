package com.example.splashkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.splashkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition{ true }
        Thread.sleep(3000)
        splashScreen.setKeepOnScreenCondition{ false }

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
    }
}