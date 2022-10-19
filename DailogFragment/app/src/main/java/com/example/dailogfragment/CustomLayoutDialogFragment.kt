package com.example.dailogfragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.dailogfragment.databinding.DialogSigninBinding

/**
 * カスタムレイアウトのDialogFragment
 */
class CustomLayoutDialogFragment : DialogFragment() {

    /** ViewBinding */
    private lateinit var binding: DialogSigninBinding

    /** DialogFragmentのアクションをActivityに通知するためのinterface */
    internal lateinit var listener: NoticeDialogListener

    /** レイアウトのユーザ名 */
    lateinit var userName: EditText

    /** レイアウトのパスワード名 */
    lateinit var password: EditText

    /**
     * オーバーライドして、独自のカスタム Dialog コンテナを構築します。
     * これは通常、一般的なダイアログの代わりに AlertDialog を表示するために使用されます。
     * その場合、AlertDialog が独自のコンテンツを処理するため、 onCreateView(LayoutInflater, ViewGroup, Bundle)を実装する必要はありません。
     * このメソッドは、 onCreate(Bundle)の後onCreateView(LayoutInflater, ViewGroup, Bundle)直前に呼び出されます。
     * デフォルトの実装は、単純にDialogクラスをインスタンス化して返します。
     * 注: DialogFragment は、 Dialog.setOnCancelListenerおよびDialog.setOnDismissListenerコールバックを所有しています。
     * 自分で設定しないでください。これらのイベントについて調べるには、 onCancel(DialogInterface)とonDismiss(DialogInterface) DialogInterface) をオーバーライドします。
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            binding = DialogSigninBinding.inflate(requireActivity().layoutInflater)
            userName = binding.username
            password = binding.password
            builder.setView(binding.root)
                .setPositiveButton("Login") { dialog, id ->
                    listener.onDialogPositiveClick(this)
                    which(dialog, id)
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    getDialog()?.cancel()
                    listener.onDialogNegativeClick(this)
                    which(dialog, id)
                }
                builder.create()
        } ?: throw java.lang.IllegalArgumentException("Activity cannot be null")
    }

    /**
     * どれをえらんだか？
     */
    fun which(daialog: DialogInterface, id: Int) {
        when (id) {
            DialogInterface.BUTTON_POSITIVE ->
                Toast.makeText(context, "Loginを押しましたね。${userName.text.toString()} ${password.text.toString()}", Toast.LENGTH_LONG).show()
            DialogInterface.BUTTON_NEGATIVE ->
                Toast.makeText(context, "Cancelを押しましたね", Toast.LENGTH_LONG).show()
            else -> {}
        }
    }

    /**
     * フラグメントがそのコンテキストに最初にアタッチされたときに呼び出されます。この後に onCreate(Bundle) が呼び出されます。
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NoticeDialogListener
        } catch (e: java.lang.ClassCastException) {
            throw java.lang.ClassCastException( (context.toString() + "must implimnet NoticeDialogListener"))
        }
    }
}