package com.example.actionbar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.actionbar.databinding.ActivityMainBinding

/**
 * ActinBarのサンプル
 */
class MainActivity : AppCompatActivity() {

    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) .apply {
            setContentView(this.root)
        }

        // ActionBarの色とタイトルを変えてみる
        supportActionBar?.setTitle("あー、ほげほげ")
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.GREEN)) // アプリバーを緑にする
    }

    /**
     * ActionBarにメニューをインフレートしてやる
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_sample, menu)
        return true
    }

    /**
     * メニューが選択された場合のハンドラ
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.iconItem -> {
                Toast.makeText(this, "アイコン付が選択されました", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_item1 -> {
                Toast.makeText(this, "アイテム１が選択されました", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_item2 -> {
                Toast.makeText(this, "アイテム２が選択されました", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_item3_1 -> {
                Toast.makeText(this, "アイテム３−１が選択されました", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_item3_2 -> {
                    Toast.makeText(this, "アイテム３−２が選択されました", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
        return true
    }
}