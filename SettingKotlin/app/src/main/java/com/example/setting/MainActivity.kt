package com.example.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.preference.PreferenceManager
import com.example.setting.databinding.ActivityMainBinding

/**
 * AndroidX Preference Libraryのサンプル
 * https://developer.android.com/guide/topics/ui/settings?hl=ja
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    /**
     * Activityの初期化処理
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
        // SharedPreferenceのデフォルトチをxmlから取得して書き込み
        PreferenceManager.setDefaultValues(applicationContext, R.xml.root_preferences, false)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, DisplayFragment())
            .commit()
    }

    /**
     * ActionBarのメニューのインフレート
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    /**
     * ActionBarのアイコンがタップされた時のハンドラ
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, SettingsFragment())
                    .commit()
            }
            R.id.disp -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, DisplayFragment())
                    .commit()
            }
            else -> {}
        }
        return true
    }
}