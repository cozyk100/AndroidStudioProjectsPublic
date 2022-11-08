package com.example.robolectric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.robolectric.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        binding.button.setOnClickListener {
            binding.textView.text = "Ohh robolectric!!"
            binding.textView2.text = "local test"
        }
    }
}