package jp.co.suzuken.datetimepickerkotlin

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate

/**
 * DatePicker ダイアログで日付を選択
 */
class DatePickerFragment(): DialogFragment(), DatePickerDialog.OnDateSetListener {

    /**
     * Fragment に選択結果を渡すためのリスナー
     */
    interface OnSelectedDateListener {
        fun selectedDate(year: Int, month: Int, dayOfMonth: Int)
    }

    /** Fragment に選択結果を渡すためのリスナー */
    private lateinit var listener: OnSelectedDateListener

    /**
     * フラグメントがそのコンテキストに最初にアタッチされるときに呼び出されます。
     * この後、onCreate(android.os.Bundle) が呼び出されます。
     * このメソッドをオーバーライドする場合は、スーパークラス実装を呼び出す必要があります。
     * @param[context] Context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment is OnSelectedDateListener) {
            listener = parentFragment as OnSelectedDateListener
        }
    }

    /**
     * オーバーライドして独自のカスタム Dialog コンテナを構築します。
     * これは通常、汎用ダイアログの代わりに AlertDialog を表示するために使用されます。
     * その際、AlertDialog が独自のコンテンツを処理するため、onCreateView(LayoutInflater, ViewGroup, Bundle) を実装する必要はありません。
     * このメソッドは、onCreate(Bundle) の後、onCreateView(LayoutInflater, ViewGroup, Bundle) の直前に呼び出されます。
     * デフォルトの実装は、単に Dialog クラスをインスタンス化して返します。
     * 注: DialogFragment は、Dialog.setOnCancelListener コールバックと Dialog.setOnDismissListener コールバックを所有します。
     * 自分で設定しないでください。これらのイベントについて確認するには、onCancel(DialogInterface) および onDismiss(DialogInterface) をオーバーライドします。
     * @param[savedInstanceState] 最後に保存されたフラグメントのインスタンス状態、または新しく作成されたフラグメントの場合は null
     * @return フラグメントによって表示される新しい Dialog インスタンスを返します。
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val localDate =LocalDate.now()
        return DatePickerDialog(
            this.context as Context,
            this,
            localDate.year,
            localDate.monthValue -1,
            localDate.dayOfMonth)
    }

    /**
     * 日付が選択されたときに呼ばれる
     * @param[view] DatePicker: ダイアログに関連付けられたピッカー
     * @param[year] 年
     * @param[month] 月(0～11)
     * @param[dayOfMonth] 日(1～31)
     */
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener.selectedDate(year, month, dayOfMonth)
    }
}