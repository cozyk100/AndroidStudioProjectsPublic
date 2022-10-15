package com.example.fragmenandactivitykotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmenandactivitykotlin.databinding.FragmentMainBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * フラグメントクラス
 * インスタンスの作成は [SampleFragment.newInstance] ファクトリメソッドを使う
 */
class SampleFragment : Fragment() {
    /** ViewBinding */
    private lateinit var binding: FragmentMainBinding
    /** フラグメントの引数 */
    private var param1: String? = null

    /**
     * フラグメントの初期作成を行うために呼び出されます。
     * これは onAttach(Activity) の後、onCreateView(LayoutInflater, ViewGroup, Bundle) の前に呼び出されます。
     * これは、フラグメントのアクティビティがまだ作成中に呼び出される可能性があることに注意してください。
     * そのため、この時点で初期化されているアクティビティのコンテンツ ビュー階層などに依存することはできません。
     * アクティビティ自体が作成された後に作業を行う場合は、アクティビティの Lifecycle に androidx.lifecycle.LifecycleObserver
     * を追加し、Lifecycle.State.CREATED コールバックを受け取ったときに削除します。
     * 復元された子フラグメントは、基本 Fragment.onCreate メソッドが戻る前に作成されます。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    /**
     * フラグメントにユーザーインターフェイスビューをインスタンス化させるために呼び出されます。
     * これはオプションであり、非グラフィックフラグメントはnullを返す可能性があります。
     * これは、onCreate（Bundle）とonViewCreated（View、Bundle）の間で呼び出されます
     * @param[inflater] フラグメント内のビューをinflateするために使用できるLayoutInflaterオブジェクト、
     * @param[container] Null以外の場合、これはフラグメントのUIをアタッチする必要がある親ビューです。
     *                  フラグメントはビュー自体を追加するべきではありませんが、これを使用してビューのLayoutParamsを生成できます。
     * @param[savedInstanceState] null以外の場合、このフラグメントは、ここに示すように、以前に保存された状態から再構築されます。
     * @return フラグメントのUIのビューを返すか、nullを返します
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
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
        val args = arguments
        args?.let {
            val str = args.getString(ARG_PARAM1)
            val textView = binding.textFtagment
            textView.text = str
        }
    }

    companion object {
        /**
         * このフラグメントの引数付きでインスタンスを作成する場合はこのファクトリメソッドを使う
         * @param[param1] Parameter 1.
         * @return SampleFragmentのインスタンス
         */
        @JvmStatic
        fun newInstance(param1: String) =
            SampleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}