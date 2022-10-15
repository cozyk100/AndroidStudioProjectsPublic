package com.example.navigationkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navigationkotlin.databinding.ActivityMainBinding

/**
 * Navigationを使ってFragmentの画面遷移をする例
 * https://developer.android.com/guide/navigation?hl=ja
 * https://qiita.com/tktktks10/items/7df56b4795d907a4cd31
 * https://qiita.com/naoi/items/8384561d30111c8704b3
 */
class MainActivity : AppCompatActivity() {
    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding

    /**
     * Activityの初期処理
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
    }
}