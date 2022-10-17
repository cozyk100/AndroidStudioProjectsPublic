package com.example.viewmodelandfragment

import androidx.lifecycle.ViewModel

/**
 * 共有するViewModel
 * ここにアイテムのリストを持つのは違うような気がするが、ViewModelを共有する目的のサンプルなので・・・
 */
class SharedViewModel: ViewModel() {

    /** アイテムのリスト */
    val itemList = mutableListOf<Item>()

    /**
     * 追加
     */
    fun addItem(item: Item) {
        itemList.add(item)
    }

    /**
     * 取得
     */
    fun getItemList(): String {
        val sb = StringBuilder()
        itemList.forEach { item ->
            sb.append(item.name + "=" + item.description + "\r\n")
        }
        return sb.toString()
    }
}