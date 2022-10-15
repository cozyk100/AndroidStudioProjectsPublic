package com.example.fragmentflowkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.fragmentflowkotlin.databinding.ActivityMainBinding

/**
 * Fragmentで画面遷移する例
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater). apply {
            setContentView(this.root)
        }
        // いきなりFragmentMainへ
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                addToBackStack(null)
                setReorderingAllowed(true) // ないとFragmentの遷移、back stack やアニメーションに問題が生じる
                replace(R.id.fragmentView, FragmentMain.newInstance("アクティビティからのメッセージ"))
            }
        }
    }
}