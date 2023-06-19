package com.example.actionbar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
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
        setupMenuBar()
    }

    /**
     * ActionBarのメニューの設定
     */
    private fun setupMenuBar() {
            // TODO Fragmentの場合
            // val menuHost: MenuHost = requireActivity()
            // menuHost.addMenuProvider(object : MenuProvider {

            addMenuProvider(object : MenuProvider {
            /**
             * MenuProvider が MenuItem をメニューにインフレートできるようにするために、MenuHost によって呼び出されます。
             * @param[menu] 新しいメニュー項目をインフレートするメニュー
             * @param[menuInflater] 更新されたメニューをインフレートさせるために使用されるインフレーター
             */
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_sample, menu)  // TODO Fragmentの場合はinflateしなくていい
            }

            /**
             * MenuItem がメニューから選択されると、MenuHost によって呼び出されます。
             * @param[item] 押されたメニュー
             * @return 指定されたメニュー項目がこのメニュー プロバイダーによって処理される場合は true、それ以外の場合は false
             */
            override fun onMenuItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.iconItem -> {
                        Toast.makeText(this@MainActivity, "アイコン付が選択されました", Toast.LENGTH_SHORT).show()
                    }
                    R.id.menu_item1 -> {
                        Toast.makeText(this@MainActivity, "アイテム１が選択されました", Toast.LENGTH_SHORT).show()
                    }
                    R.id.menu_item2 -> {
                        Toast.makeText(this@MainActivity, "アイテム２が選択されました", Toast.LENGTH_SHORT).show()
                    }
                    R.id.menu_item3_1 -> {
                        Toast.makeText(this@MainActivity, "アイテム３−１が選択されました", Toast.LENGTH_SHORT).show()
                    }
                    R.id.menu_item3_2 -> {
                        Toast.makeText(this@MainActivity, "アイテム３−２が選択されました", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
                return true
            }
        })
    }
}