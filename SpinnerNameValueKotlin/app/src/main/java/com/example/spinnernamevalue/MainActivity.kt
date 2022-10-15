package com.example.spinnernamevalue

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.spinnernamevalue.databinding.ActivityMainBinding

/**
 * Spinnerを名称と値でペアで持つ例
 */
class MainActivity : AppCompatActivity() {
    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = binding.spinner
        val editText = binding.editText
        val textView = binding.textView
        val button = binding.button

        // spinnerに表示するリストと作ってやる
        val myItemList = ArrayList<MyItem>()
        myItemList.add(MyItem("1", "名称１"))
        myItemList.add(MyItem("2", "名称２"))
        myItemList.add(MyItem("3", "名称３"))
        myItemList.add(MyItem("4", "名称４"))
        myItemList.add(MyItem("5", "名称５"))
        val myAdapter = MyAdapter(this, myItemList)
        spinner.adapter = myAdapter
        // spinnerが選択されたときのリスナ
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            //　アイテムが選択された時
            override fun onItemSelected(parent: AdapterView<*>?,view: View?, position: Int, id: Long) {
                val spinnerParent = parent as Spinner
                val myItem = spinnerParent.selectedItem as MyItem
                textView.text = myItem.name
                editText.setText(myItem.code)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        // ボタンが押されたときのリスナ、EditTextの値でSpinnerを選択してやる
        button.setOnClickListener {
            val code = editText.text.toString()
            myItemList.forEachIndexed { index, myItem ->
                if (code == myItem.code) {
                    spinner.setSelection(index)
                    return@forEachIndexed
                }
            }
        }

    }
}

/**
 * Spinnerに設定するアダプタ
 * @constructor
 * @param[context] Context
 * @param[list] Spinnerに表示するリスト
 */
class MyAdapter(context: Context, list: ArrayList<MyItem>) :
    ArrayAdapter<MyItem>(context, android.R.layout.simple_spinner_item, list) {

    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    /**
     * 選択中の項目の表示レイアウトを生成する
     * データ セット内の指定された位置にデータを表示するビューを取得します。
     * ビューを手動で作成するか、XML レイアウト ファイルからインフレートすることができます。ビューが膨張すると、
     * 親ビュー (GridView、ListView...) は、使用しない限り、既定のレイアウト パラメーターを適用します。
     * @param[position] ビューが必要なアイテムのアダプターのデータセット内のアイテムの位置。
     * @param[convertView] nullの可能性あり
     * @param[parent] null以外
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getView(position, convertView, parent) as TextView
        textView.text = getItem(position)?.name ?: ""
        return textView
    }

    /**
     * リストのレイアウトを生成する
     * データ セット内の指定された位置にあるデータをドロップダウン ポップアップに表示するビューを取得します。
     * @param[position] ビューが必要なアイテムのインデックス
     * @param[convertView] nullの可能性あり
     * @param[parent] null以外
     */
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getDropDownView(position, convertView, parent) as TextView
        textView.text = getItem(position)?.name ?: ""
        return textView
    }
}

/** Spinnerに表示する値と名称のデータクラス */
data class MyItem(var code: String, var name: String)