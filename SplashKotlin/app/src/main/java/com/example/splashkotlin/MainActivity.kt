package com.example.splashkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * スプラッシュ・スクリーンの例
 * https://developer.android.com/guide/topics/ui/splash-screen?hl=ja
 * https://qiita.com/karass/items/bcb50607c0b9fc21f183
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SplashKotlin)
        Thread.sleep(1000) // 早すぎるのでsleep
        setContentView(R.layout.activity_main)
    }
}