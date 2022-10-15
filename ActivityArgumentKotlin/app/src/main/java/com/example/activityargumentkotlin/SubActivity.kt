package com.example.activityargumentkotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.activityargumentkotlin.MainActivity.Companion.EXTRA_MESSAGE
import com.example.activityargumentkotlin.databinding.ActivitySubBinding

/**
 * サブアクティビティ
 */
class SubActivity : AppCompatActivity() {
    /** ViewBinding */
    private lateinit var binding: ActivitySubBinding

    /**
     * サブアクティビティの初期化処理
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // メインからの受取
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        binding.textView.text = message

        // ボタンのイベント サブ→メイン
        binding.button.setOnClickListener {
            val intentSub = Intent()
            if (binding.editText.text != null) {
                // メインに返す値を設定
                val str = message + binding.editText.text.toString()
                intentSub.putExtra(EXTRA_MESSAGE, str)
                binding.editText.setText("")
            }
            setResult(Activity.RESULT_OK, intentSub)
            finish() // メインに戻る
        }
    }
}