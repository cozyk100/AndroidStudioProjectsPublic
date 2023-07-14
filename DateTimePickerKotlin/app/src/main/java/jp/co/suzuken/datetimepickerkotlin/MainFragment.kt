package jp.co.suzuken.datetimepickerkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.co.suzuken.datetimepickerkotlin.databinding.FragmentMainBinding

/**
 * メインのフラグメント
 */
class MainFragment : Fragment(),
    DatePickerFragment.OnSelectedDateListener, // DatePicker
    TimePickerFragment.OnSelectedTimeListener { // TimePicker

    /** ViewBinding */
    private lateinit var binding: FragmentMainBinding

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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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

        binding.dateBtn.setOnClickListener {
            DatePickerFragment().show(childFragmentManager, "DatePicker")
        }
        binding.timeBtn.setOnClickListener {
            TimePickerFragment().show(childFragmentManager, "TimePicker")
        }
    }

    /**
     * DatePickerDialogで日付が選択されたとき、TextViewを更新
     * @param[year] 年
     * @param[month] 月(0～11)
     * @param[dayOfMonth] 日
     */
    override fun selectedDate(year: Int, month: Int, dayOfMonth: Int) {
        binding.dateText.text = String.format("%04d/%02d/%02d", year, month + 1, dayOfMonth )
    }

    /**
     * TimePickerDialogで時間が選択されたとき、TextViewを更新
     * @param[hour] 時間
     * @param[minute] 分
     */
    override fun selectedTime(hour: Int, minute: Int) {
        binding.timeText.text = String.format("%02d:%02d", hour, minute)
    }
}