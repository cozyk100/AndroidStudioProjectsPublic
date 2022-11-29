package com.example.blinktextview

import android.animation.Animator
import android.animation.AnimatorInflater
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blinktextview.databinding.ActivityMainBinding

/**
 * Viewを点滅させる
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    /** AnimatorSetオブジェクト */
    private lateinit var set: Animator
    private lateinit var set2: Animator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
        //AnimatorInflaterで、Animatorブジェクトを取得
        //前もって作成したR.animator.blink_animationをインフレート
        set = AnimatorInflater.loadAnimator(this, R.animator.blink_animation)
        set2 = AnimatorInflater.loadAnimator(this, R.animator.blink_animation)
        //アニメーション対称のオブジェクトを設定
        set.setTarget(binding.helloText)
        set2.setTarget(binding.button)
    }

    override fun onStart() {
        super.onStart()
        //アニメーションの開始
        set.start()
        set2.start()
    }
}