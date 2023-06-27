package com.example.toolbar

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
import androidx.lifecycle.Lifecycle
import com.example.toolbar.databinding.Fragment2Binding

/**
 * フラグメント２
 */
class Fragment2 : Fragment() {
    /** ViewBinding */
    private lateinit var binding: Fragment2Binding

    /**
     * フラグメントの初期作成を行うために呼び出されます。
     * これは onAttach(Activity) の後、onCreateView(LayoutInflater, ViewGroup, Bundle) の前に呼び出されます。
     * これは、フラグメントのアクティビティがまだ作成中に呼び出される可能性があることに注意してください。
     * そのため、この時点で初期化されているアクティビティのコンテンツ ビュー階層などに依存することはできません。
     * アクティビティ自体が作成された後に作業を行う場合は、アクティビティの Lifecycle に androidx.lifecycle.LifecycleObserver
     * を追加し、Lifecycle.State.CREATED コールバックを受け取ったときに削除します。
     * 復元された子フラグメントは、基本 Fragment.onCreate メソッドが戻る前に作成されます。
     * @param[inflater] フラグメント内の任意のビューをインフレートするために使用できる LayoutInflater オブジェクト
     * @param[container] null 以外の場合、これはフラグメントの UI がアタッチされる親ビューです。
     *                   フラグメントはビュー自体を追加するべきではありませんが、これを使用してビューの LayoutParams を生成できます。
     * @param[savedInstanceState] null 以外の場合、このフラグメントは、ここで指定されているように、以前に保存された状態から再構築されています。
     * @return フラグメントの UI のビュー、または null を返します。
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * onCreateView（LayoutInflater、ViewGroup、Bundle）が戻った直後、ただし保存された状態がビューに復元される前に呼び出されます。
     * これにより、サブクラスは、ビュー階層が完全に作成されたことがわかったら、自分自身を初期化する機会が与えられます。
     * ただし、フラグメントのビュー階層は、この時点ではその親にアタッチされていません。
     * @param[view] onCreateView（LayoutInflater、ViewGroup、Bundle）によって返されるビュー
     * @param[savedInstanceState] Null以外の場合、このフラグメントは、ここに示すように、以前に保存された状態から再構築されます
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenuBar()
        binding.button.setOnClickListener {
            parentFragmentManager.popBackStack()
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
                menu.findItem(R.id.iconItem1).isVisible = true // アイコン
                menu.findItem(R.id.iconItem2).isVisible = false // アイコン
                menu.findItem(R.id.menu_item3_1).isVisible = true // メニュー
                menu.findItem(R.id.menu_item3_2).isVisible = false // メニュー
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