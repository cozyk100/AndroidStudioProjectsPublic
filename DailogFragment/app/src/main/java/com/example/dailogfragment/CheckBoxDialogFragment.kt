package com.example.dailogfragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 * CheckBoxを表示するDialogFragment
 */
class CheckBoxDialogFragment : DialogFragment() {

    /** DialogFragmentのアクションをActivityに通知するためのinterface */
    internal lateinit var listener: NoticeDialogListener

    /** 選択したidのリスト */
    val selectedItems = ArrayList<Int>()

    /** チェックボックスの一覧 */
    val toppingList = arrayOf("オニオン", "レタス", "トマト")

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
            builder.setTitle("トッピング選んでください")
                .setMultiChoiceItems(toppingList, null) { dialog, which, isChecked ->
                    if (isChecked) {
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        selectedItems.remove(which)
                    }
                }
                .setPositiveButton("OK") { dialog, id ->
                    listener.onDialogPositiveClick(this)
                    which(dialog, id)
                }
                .setNegativeButton("Cancel") { dialog, id ->
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
            DialogInterface.BUTTON_POSITIVE -> {
                val sb = StringBuilder()
                selectedItems.forEach { it ->
                    sb.append(toppingList[it] + ",")
                }
                Toast.makeText(context, "OKを押しましたね。${sb.toString()}", Toast.LENGTH_LONG).show()
            }
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