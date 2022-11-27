package com.example.setting

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.setting.databinding.FragmentDisplayBinding

/**
 * SharedPreferenceの内容を表示するFragment
 */
class DisplayFragment : Fragment() {
    private lateinit var binding: FragmentDisplayBinding

    /**
     * Fragmentの初期化処理
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * フラグメントの初期作成を行うために呼び出されます。
     * これは onAttach(Activity) の後、onCreateView(LayoutInflater, ViewGroup, Bundle) の前に呼び出されます。
     * これは、フラグメントのアクティビティがまだ作成中に呼び出される可能性があることに注意してください。
     * そのため、この時点で初期化されているアクティビティのコンテンツ ビュー階層などに依存することはできません。
     * アクティビティ自体が作成された後に作業を行う場合は、アクティビティの Lifecycle に androidx.lifecycle.LifecycleObserver
     * を追加し、Lifecycle.State.CREATED コールバックを受け取ったときに削除します。
     * 復元された子フラグメントは、基本 Fragment.onCreate メソッドが戻る前に作成されます。
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDisplayBinding.inflate(inflater, container, false)
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
        val sharedPrefence = activity?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        binding.textPref.text = sharedPrefence?.getString("textPreference", "") ?: ""
        binding.listPref.text = sharedPrefence?.getString("listPreference", "") ?: ""
        binding.checkPref1.text = sharedPrefence?.getBoolean("checkboxPreference1", false) .toString() ?: "false"
        binding.checkPref2.text = sharedPrefence?.getBoolean("checkboxPreference2", false) .toString() ?: "false"
        binding.checkPref3.text = sharedPrefence?.getBoolean("checkboxPreference3", false) .toString() ?: "false"
        binding.switchPref.text = sharedPrefence?.getBoolean("switchPreference", false) .toString() ?: "false"
    }
}