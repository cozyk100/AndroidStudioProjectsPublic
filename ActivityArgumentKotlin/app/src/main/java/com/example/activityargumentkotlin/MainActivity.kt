package com.example.activityargumentkotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.activityargumentkotlin.databinding.ActivityMainBinding

/**
 * メインアクティビティ
 */
class MainActivity : AppCompatActivity() {

    companion object {
        /** Intentを通してデータを渡す時のキー */
        const val EXTRA_MESSAGE = "activityargumentkotlin.MESSAGE"
    }

    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding

    /** サブアクティビティからの戻り */
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val res = it.data?.getStringExtra(EXTRA_MESSAGE)
                binding.textView.text = res
            }
        }

    /**
     * メインアクティビティの初期化処理
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ボタンのイベント メイン→サブ
        binding.button.setOnClickListener {
            if (binding.editText.text != null) {
                val intent = Intent(applicationContext, SubActivity::class.java)
                // サブに渡す値を設定
                val str = binding.editText.text.toString()
                intent.putExtra(EXTRA_MESSAGE, str)
                // サブを呼ぶ
                getResult.launch(intent)
                binding.editText.setText("")
            }
        }
    }
}