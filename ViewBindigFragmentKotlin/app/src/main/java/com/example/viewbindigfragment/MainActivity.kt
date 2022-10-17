package com.example.viewbindigfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewbindigfragment.databinding.ActivityMainBinding

/**
 * メインアクティビティ
 */
class MainActivity : AppCompatActivity() {
    /** ViewBinding */
     lateinit var binding: ActivityMainBinding

    /**
     * onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = binding.button
        if (savedInstanceState == null) {
            button.setOnClickListener {
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                // TODO ここは第一引数がInt型しなないので、R.id.xxxで指定するしかないのか？
                fragmentTransaction.replace(R.id.FrameLayout, SampleFragment.newInstance("ほげほげ"))
                fragmentTransaction.commit()
            }
        }
    }
}