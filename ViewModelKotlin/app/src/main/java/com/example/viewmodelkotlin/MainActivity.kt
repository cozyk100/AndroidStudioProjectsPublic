package com.example.viewmodelkotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelkotlin.databinding.ActivityMainBinding

/**
 * 一番シンプルなViewModelの例
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // android.arch.lifecycle.ViewModelProvider や、
    // android.arch.lifecycle.ViewModelProviders はもうメンテされないので
    // androidx.lifecycle.ViewModelProviders を使う
    private val viewModel: CountViewModel by viewModels<CountViewModel> {
        CountViewModelFactory(SomeRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        var counterA = 0
        binding.button.setOnClickListener {
            counterA++
            viewModel.counterB++
            // 回転した瞬間は両方ゼロになるが、ごれはしょうがない
            binding.text1.text= counterA.toString()
            binding.text2.text = viewModel.counterB.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart()が呼ばれました")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume()が呼ばれました")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause()が呼ばれました")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop()が呼ばれました")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy()が呼ばれました")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart()が呼ばれました")
    }
}
