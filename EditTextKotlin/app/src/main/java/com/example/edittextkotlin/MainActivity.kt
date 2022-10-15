package com.example.edittextkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.edittextkotlin.databinding.ActivityMainBinding

/**
 * EditTextの例
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextText = binding.editTextText
        val editTextNumber = binding.editTextNumber
        val editTextNumberSigned = binding.editTextNumberSigned
        val editTextNumberDecimal = binding.editTextNumberDecimal
        val editTextDate = binding.editTextDate
        val editTextTime = binding.editTextTime
        val editTextTextPassword = binding.editTextTextPassword
        val editTextTextMultiline = binding.editTextTextMultiline

        editTextText.setOnEditorActionListener(editorAction)
        editTextNumber.setOnEditorActionListener(editorAction)
        editTextNumberSigned.setOnEditorActionListener(editorAction)
        editTextNumberDecimal.setOnEditorActionListener(editorAction)
        editTextDate.setOnEditorActionListener(editorAction)
        editTextTime.setOnEditorActionListener(editorAction)
        editTextTextPassword.setOnEditorActionListener(editorAction)
        editTextTextMultiline.setOnEditorActionListener(editorAction)

        editTextText.setOnFocusChangeListener(focusChange)
        editTextNumber.setOnFocusChangeListener(focusChange)
        editTextNumberSigned.setOnFocusChangeListener(focusChange)
        editTextNumberDecimal.setOnFocusChangeListener(focusChange)
        editTextDate.setOnFocusChangeListener(focusChange)
        editTextTime.setOnFocusChangeListener(focusChange)
        editTextTextPassword.setOnFocusChangeListener(focusChange)
        editTextTextMultiline.setOnFocusChangeListener(focusChange)

    }

    private val editorAction: TextView.OnEditorActionListener = object: TextView.OnEditorActionListener {
        override fun onEditorAction(view: TextView, actionId: Int, event: KeyEvent?): Boolean {
            val vName = viewName(view.id)
            Log.d("editorAction", "$vName")
            when (event?.action) {
                KeyEvent.ACTION_UP -> "KeyEvent.ACTION_UP"
                KeyEvent.ACTION_DOWN -> "KeyEvent.ACTION_DOWN"
                else -> ""
            }
            when (event?.keyCode) {
                KeyEvent.KEYCODE_ENTER -> "KeyEvent.KEYCODE_ENTER"
                KeyEvent.KEYCODE_BACK -> "KeyEvent.KEYCODE_BACK"
                else -> "他"
            }
            return if(actionId == EditorInfo.IME_ACTION_DONE) {
                // ActionButton押下後の処理
                Log.d("editorAction", "EditorInfo.IME_ACTION_DONE")
                true
            } else if(actionId == EditorInfo.IME_ACTION_NEXT) {
                Log.d("editorAction", "EditorInfo.IME_ACTION_NEXT")
                true
            } else if(actionId == EditorInfo.IME_ACTION_PREVIOUS) {
                Log.d("editorAction", "EditorInfo.IME_ACTION_PREVIOUS")
                true
            } else if(actionId == EditorInfo.IME_ACTION_GO) {
                Log.d("editorAction", "EditorInfo.IME_ACTION_GO")
                true
            } else if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.d("editorAction", "EditorInfo.IME_ACTION_SEARCH")
                true
            } else if(actionId == EditorInfo.IME_ACTION_SEND) {
                Log.d("editorAction", "EditorInfo.IME_ACTION_SEND")
                true
            } else if(event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                // Enter押下後の処理
                Log.d("editorAction", "KeyEvent.KEYCODE_ENTER")
                true
            } else false
        }
    }

    private val focusChange: View.OnFocusChangeListener = object: View.OnFocusChangeListener {
        override fun onFocusChange(view: View?, hasFocus: Boolean) {
            val vName = view?.let { viewName(it.id) }
            Log.d("focusChange", "$vName")
            if (hasFocus) {
                // フォーカスが当たった
                Log.d("focusChange", "フォーカスが当たった")
            } else {
                // フォカスが外れた
                Log.d("focusChange", "フォカスが外れた")
            }
        }

    }

    private fun viewName(id: Int) =  when(id) {
        binding.editTextText.id -> "editTextText"
        binding.editTextNumber.id -> "editTextNumber"
        binding.editTextNumberSigned.id -> "editTextNumberSigned"
        binding.editTextNumberDecimal.id -> "editTextNumberDecimal"
        binding.editTextDate.id -> "editTextDate"
        binding.editTextTime.id -> "editTextTime"
        binding.editTextTextPassword.id -> "editTextTextPassword"
        binding.editTextTextMultiline.id -> "editTextTextMultiline"
        else -> ""
    }
}