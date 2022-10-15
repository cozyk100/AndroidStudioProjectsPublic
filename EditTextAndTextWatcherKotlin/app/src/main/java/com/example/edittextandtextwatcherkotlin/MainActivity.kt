package com.example.edittextandtextwatcherkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.edittextandtextwatcherkotlin.databinding.ActivityMainBinding

/**
 * EditTextとTextWawtcherのサンプル
 * https://akira-watson.com/android/textwatcher.html
 */
class MainActivity : AppCompatActivity() {
    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        val editText = binding.editText
        editText.addTextChangedListener(textWatcherListener)
    }

    /**
     * TextWatcher
     */
    private val textWatcherListener: TextWatcher = object: TextWatcher {

        /**
         * 文字列が修正される直前に呼び出されるメソッド
         * @param[s] 現在EditTextに入力されている文字列
         * @param[start] sの文字列で新たに追加される文字列のスタート位置
         * @param[count] sの文字列の中で変更された文字列の総数
         * @param[after] 新規に追加された文字列の数
         */
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            Log.d("beforeTextChanged", "s=${s} start=${start} count=${count} after=${after}")
        }

        /**
         * 文字１つを入力した時に呼び出される
         * @param[s] 現在EditTextに入力されている文字列
         * @param[start] sの文字列で新たに追加される文字列のスタート位置
         * @param[before] 削除される既存文字列の数
         * @param[count] 新たに追加された文字列の数
         */
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            Log.d("onTextChanged", "s=${s} start=${start} before=${before} count=${count}")
        }

        /**
         * 最後にこのメソッドが呼び出される
         * @param[s] 最終的にできた修正可能な、変更された文字列
         */
        override fun afterTextChanged(s: Editable?) {
            Log.d("afterTextChanged", "s=${s}")
            binding.textView.text = s
            if (s.toString().length > 5) {
                binding.editText.error = "5桁以内で入力してください"
            }
        }
    }
}