package com.example.fragmenandactivitykotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.fragmenandactivitykotlin.databinding.ActivityMainBinding

/**
 * メインアクティビティ
 */
class MainActivity : AppCompatActivity() {
    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding

    /**
     * メインアクティビティの初期化
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // BundleのsavedInstanceStateがnull、何もないときだけ設定をするようにする。
        // FragmentはActivityのライフサイクル中に何度でも呼ばれることが可能なので最初だけ設定をするようにする。
        if (savedInstanceState == null) {
            // ボタンのイベント
            binding.button.setOnClickListener {
                // 古い書き方だと、こう？
//                val fragmentManager = supportFragmentManager
//                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.addToBackStack(null)
//                fragmentTransaction.replace(R.id.container, SampleFragment.newInstance(getString(R.string.message)))
//                fragmentTransaction.commit()
                // これでもイケる
                supportFragmentManager.commit {
                    addToBackStack(null)
                    setReorderingAllowed(true) // ないとFragmentの遷移、back stack やアニメーションに問題が生じる
                    replace(R.id.container, SampleFragment.newInstance(getString(R.string.message)))
                    // fragmentTransaction.replaceか？addか？
                    // https://developer.android.com/training/basics/fragments/fragment-ui?hl=ja
                    // https://developer.android.com/guide/fragments/fragmentmanager?hl=ja
                }
            }
        }
    }
}