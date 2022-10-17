package com.example.viewbindigfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viewbindigfragment.databinding.FragmentMainBinding

/**
 * フラグメント
 */
class SampleFragment : Fragment() {
    /** ViewBinding */
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        /**
         * フラグメントのインスタンスを返す
         */
        fun newInstance(param: String): SampleFragment {
            val sampleFragment = SampleFragment()
            val args = Bundle()
            args.putString("PARAM", param)
            sampleFragment.arguments = args
            return sampleFragment
        }
    }

    /**
     * なくてもいいのか？
     * @param[savedInstanceState] Bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
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
        binding.button.setOnClickListener {
            val param = args?.getString("PARAM")
            binding.textView3.text = param
            // これがあるとtestでmockの設定ができない。
            // 本来はこんなことはしてはいけないのかもしれない。ちゃんと引数で渡すべき
            // val activityBinding = (requireActivity() as MainActivity).binding
            // binding.textView5.text = activityBinding.editText.text.toString()
        }
    }

    /**
     * onCreateView によって以前に作成されたビューがフラグメントから切り離されたときに呼び出されます。
     * 次にフラグメントを表示する必要があるときに、新しいビューが作成されます。
     * これは、onStop() の後、onDestroy() の前に呼び出されます。 onCreateView が null 以外のビューを返したかどうかに関係なく呼び出されます。
     * 内部的には、ビューの状態が保存された後、親から削除される前に呼び出されます。
     */
    override fun onDestroyView() {
        super.onDestroyView()
        // bindingを破棄
        _binding = null
    }
}