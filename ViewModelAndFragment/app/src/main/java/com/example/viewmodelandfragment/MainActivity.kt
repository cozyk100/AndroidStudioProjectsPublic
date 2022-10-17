package com.example.viewmodelandfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.example.viewmodelandfragment.databinding.ActivityMainBinding

/**
 * Fragment間でViewModelを共有するサンプル
 * https://developer.android.com/topic/libraries/architecture/viewmodel?hl=JA#sharing
 */
class MainActivity : AppCompatActivity() {
    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding
    /** ViewModel */
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        // Activityから初期値を入れてみる
        sharedViewModel.addItem(Item("てすと", "てすと"))

        // FragmentMainへ
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                addToBackStack(null)
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView, Fragment1()) // 引数なしだとnewInstance()要らないのでは？
            }
        }
    }
}