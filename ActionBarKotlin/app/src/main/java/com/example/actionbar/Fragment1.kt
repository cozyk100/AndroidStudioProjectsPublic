package com.example.actionbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import com.example.actionbar.databinding.Fragment1Binding

/**
 * フラグメント１
 */
class Fragment1 : Fragment() {

    /** ViewBinding */
    private lateinit var binding: Fragment1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenuBar()
        binding.button.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView, Fragment2())
            }
        }
    }

    /**
     * ActionBarのメニューの設定
     */
    private fun setupMenuBar() {
         val menuHost: MenuHost = requireActivity()
         menuHost.addMenuProvider(object : MenuProvider {
            /**
             * MenuProvider が MenuItem をメニューにインフレートできるようにするために、MenuHost によって呼び出されます。
             * @param[menu] 新しいメニュー項目をインフレートするメニュー
             * @param[menuInflater] 更新されたメニューをインフレートさせるために使用されるインフレーター
             */
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
               // Fragmentの場合はinflateしなくていい
            }

             /**
              * メニューが表示される直前に MenuHost によって呼び出されます。これは、メニューが動的に更新されたときに呼び出す必要があります。
              * @param[menu] 用意する予定のメニュー
              */
             override fun onPrepareMenu(menu: Menu) {
                 // Fragment毎に、ActionBarのicon、menuの表示/非表示を制御する場合はここでやる
                 menu.findItem(R.id.iconItem1).isVisible = false // アイコン
                 menu.findItem(R.id.iconItem2).isVisible = true // アイコン
                 menu.findItem(R.id.menu_item3_1).isVisible = false // メニュー
                 menu.findItem(R.id.menu_item3_2).isVisible = true // メニュー
             }

             /**
             * MenuItem がメニューから選択されると、MenuHost によって呼び出されます。
             * @param[item] 押されたメニュー
             * @return 指定されたメニュー項目がこのメニュー プロバイダーによって処理される場合は true、それ以外の場合は false
             */
            override fun onMenuItemSelected(item: MenuItem): Boolean {
                 // アイコン、メニューが押された場合のハンドリングはMainActivityでやる
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}