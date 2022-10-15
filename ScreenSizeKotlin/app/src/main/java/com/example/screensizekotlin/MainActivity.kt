package com.example.screensizekotlin

import android.app.Activity
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import com.example.screensizekotlin.databinding.ActivityMainBinding

/**
 * Screenサイズを取得する
 * https://akira-watson.com/android/screen-size.html
 *
 * adbでも取れる
 * >adb shell wm size
 * Physical size: 1440x2560
 */
class MainActivity : AppCompatActivity() {
// class MainActivity : Activity() { // タイトルバーをなしにする、または ThemeでNoActionBarにする
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        // ActionBarの色とタイトルを変えてみる
        supportActionBar?.setTitle("あー、ほげほげ")
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.GREEN)) // アプリバーを白にする

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            // API29 Andrid Q(10)まで
            val wm = getSystemService(WINDOW_SERVICE) as WindowManager
            val disp = wm.defaultDisplay
            var realsize = Point()
            disp.getRealSize(realsize)
            Log.d("MainActivity", "ScreenWidth=${realsize.x}")
            Log.d("MainActivity", "ScreenHeight=${realsize.y}")
        } else {
            // API30 Andrid R(11)から
            val windowMetric = windowManager.currentWindowMetrics
            val insets = windowMetric.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            Log.d("MainActivity", "ScreenWidth=${windowMetric.bounds.width()}")
            Log.d("MainActivity", "ScreenHeight=${windowMetric.bounds.height()}")
            Log.d("MainActivity", "StatusBar=${insets.top}")
            Log.d("MainActivity", "NavigationBar=${insets.bottom}")
        }
    }

    /**
     * レイアウトのサイズ
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d("MainActivity", "LayoutWidth=${binding.constraintLayout.width}")
        Log.d("MainActivity", "LayoutHeight=${binding.constraintLayout.height}")
    }
}