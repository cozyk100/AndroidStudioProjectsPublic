package jp.co.suzuken.datetimepickerkotlin

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalTime

/**
 * TimePicker ダイアログで時間を選択
 */
class TimePickerFragment(): DialogFragment(), TimePickerDialog.OnTimeSetListener {

    /**
     * Fragment に選択結果を渡すためのリスナー
     */
    interface OnSelectedTimeListener {
        fun selectedTime(hour: Int, minute: Int)
    }

    /** Fragment に選択結果を渡すためのリスナー */
    private lateinit var listener: OnSelectedTimeListener

    /**
     * フラグメントがそのコンテキストに最初にアタッチされるときに呼び出されます。
     * この後、onCreate(android.os.Bundle) が呼び出されます。
     * このメソッドをオーバーライドする場合は、スーパークラス実装を呼び出す必要があります。
     * @param[context] Context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment is OnSelectedTimeListener) {
            listener = parentFragment as OnSelectedTimeListener
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
        val localTime = LocalTime.now()
        return TimePickerDialog(
            this.context as Context,
            this,
            localTime.hour,
            localTime.minute,
            true)
    }

    /**
     * 時間が選択されたときに呼ばれる
     * @param[view] DatePicker: ダイアログに関連付けられたピッカー
     * @param[hourOfDay] 時間
     * @param[minute] 分
     */
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener.selectedTime(hourOfDay, minute)
    }
}