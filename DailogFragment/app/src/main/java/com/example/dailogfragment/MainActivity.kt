package com.example.dailogfragment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.example.dailogfragment.databinding.ActivityMainBinding

/**
 * 色々なDialog、DialogFragmentのサンプル
 * https://developer.android.com/guide/topics/ui/dialogs?hl=ja
 */
class MainActivity : FragmentActivity(), NoticeDialogListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
        // 普通のAlertDialog
        binding.normal.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("タイトル")
                .setMessage("メッセージ")
                .setPositiveButton("OK") { dialog,  id ->
                    Toast.makeText(applicationContext, "OK押しましたね", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancel") {dialog, id ->
                    Toast.makeText(applicationContext, "Cancel押しましたね", Toast.LENGTH_LONG).show()
                }
                .setNeutralButton("Late") {dialog, id ->
                    Toast.makeText(applicationContext, "Late押しましたね", Toast.LENGTH_LONG).show()
                }
                .show()
        }
        // 基本形のDialogFragment
        binding.basic.setOnClickListener {
            BasicDialogFragment().show(supportFragmentManager, "Basic Dialog")
        }
        // List形式のDialogFragment
        binding.list.setOnClickListener {
            ListDialogFragment().show(supportFragmentManager, "List Dialog")
        }
        // チェックボックス形式のDialogFragment
        binding.checkbox.setOnClickListener {
            CheckBoxDialogFragment().show(supportFragmentManager, "CheckBox Dialog")
        }
        // カスタムレイアウトのDialogFragment
        binding.custom.setOnClickListener {
            CustomLayoutDialogFragment().show(supportFragmentManager,"Custom Layout")
        }
    }

    /**
     * OK(Positive)が押された場合
     */
    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Log.d("MainActivity", "onDialogPositiveClick")
        when (dialog) {
            // リスト形式の場合
            is ListDialogFragment -> {
                when (dialog.selectId) {
                    0 -> binding.textView.text = "赤を押しましたね"
                    1 -> binding.textView.text = "青を押しましたね"
                    2 -> binding.textView.text = "黄を押しましたね"
                    else -> {}
                }
            }
            // チェックボックス形式の場合
            is CheckBoxDialogFragment -> {
                val sb = StringBuilder()
                dialog.selectedItems.forEach { it ->
                    sb.append(dialog.toppingList[it] + "\r\n")
                }
                binding.textView.text = sb.toString()
            }
            // カスタムレイアウトの場合
            is CustomLayoutDialogFragment -> {
                binding.textView.text =
                    String.format("%s\n\r%s", dialog.userName.text.toString(), dialog.password.text.toString())
            }
            else -> {}
        }
    }

    /**
     * Cancel(Negative)が押された場合
     */
    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Log.d("MainActivity", "onDialogNegativeClick")
    }

    /**
     * どちらでもない(Neutral)が押された場合
     */
    override fun onDialogNeutralClick(dialog: DialogFragment) {
        Log.d("MainActivity", "onDialogNeutralClick")
    }
}