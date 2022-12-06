package com.example.radionamevalue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.example.radionamevalue.databinding.ActivityMainBinding

/**
 * Radioボタンの名前を値をペアで持つ例
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        // これはダサい・・・
        // レイアウトのデザインがコードに影響する
//        binding.button.setOnClickListener {
//            val id = binding.radioGroup.checkedRadioButtonId
//            val radioTxt = findViewById<RadioButton>(id).text.toString()
//            when (radioTxt) {
//                "ラジオ1" -> binding.radioValue.text = "ラジオ1が選択されました"
//                "ラジオ2" -> binding.radioValue.text = "ラジオ2が選択されました"
//                else -> {}
//            }
//        }

        // できるだけライアウトのデザインがコーディングに影響しないように書くとこうなる。
        binding.button.setOnClickListener {
            val id = binding.radioGroup.checkedRadioButtonId
            val radioVal = findViewById<RadioButton>(id).getTag() as String
            binding.radioValue.text = "ラジオ${radioVal}が選択されました"
        }

        // RadiGroupのOnCheckedChangeListenerを使ってラジの選択を変更するたびにリスナを呼び出す方法
        binding.radioGroup2.setOnCheckedChangeListener { radioGroup, i ->
            val id = radioGroup.checkedRadioButtonId
            val radioVal = findViewById<RadioButton>(id).getTag() as String
            binding.radioOnCheckValue.text = "ラジオ${radioVal}が選択されました"
        }
    }
}