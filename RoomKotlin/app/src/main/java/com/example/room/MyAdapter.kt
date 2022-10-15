package com.example.room

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.MyTextViewBinding
import com.example.room.entity.UserView

/**
 * RecyclerViewのAdapter
 */
class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    /**
     * DiffUtilのコールバック
     */
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserView>() {
            /**
             * 2つのアイテム自体が同じものであるかを判定します
             */
            override fun areItemsTheSame(oldItem: UserView, newItem: UserView): Boolean {
                return oldItem === newItem
            }

            /**
             * 表示する内容が前後で異なっているかどうかを判定します。 falseを返す場合Viewが再利用されます。
             * このメソッドはareItemsTheSameがtrueを返す場合にのみ呼ばれます。
             */
            override fun areContentsTheSame(oldItem: UserView, newItem: UserView): Boolean {
                return oldItem.uid == newItem.uid
                        && oldItem.firstName == newItem.firstName
                        && oldItem.lastName == newItem.lastName
                        && oldItem.deptName == newItem.deptName
            }
        }
    }

    /**
     * AsyncListDiffer
     */
    private val mdiffer: AsyncListDiffer<UserView> = AsyncListDiffer(this, DIFF_CALLBACK)

    /**
     * ViewHolder
     * @constructor
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        /** ViewBinding */
        private var binding: MyTextViewBinding
        var firstName: TextView
        var lastName: TextView
        var deptName: TextView

        /** 初期化 */
        init {
            binding = MyTextViewBinding.bind(view)
            firstName = binding.firstName
            lastName = binding.lastName
            deptName = binding.deptName
        }

        /**
         * holderに値を設定
         */
        fun bindTo(userView: UserView) {
            firstName.text = userView.firstName
            lastName.text = userView.lastName
            deptName.text = userView.deptName
        }
    }

    /**
     * RecyclerViewが新しいRecyclerViewを必要とするときに呼び出されます
     * @param parent 新しいビューがアダプター位置にバインドされた後に追加されるViewGroup
     * @param viewType 新しいビューのビュータイプ
     * @return 指定されたビュータイプのビューを保持する新しいViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MyTextViewBinding.inflate(layoutInflater, parent, false)
        val holder = ViewHolder(binding.root)
        binding.root.setOnClickListener {
            itemClickListener.onItemClick(holder)
        }
        return holder
    }

    /**
     * 指定された位置にデータを表示するためにRecyclerViewによって呼び出されます
     * @param holder データセット内の指定された位置にあるアイテムのコンテンツを表すように更新する必要があるViewHolder。
     * @param position アダプターのデータ・セット内のアイテムの位置
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userView = mdiffer.currentList[position]
        holder.bindTo(userView)
    }

    /**
     * アダプタの中身の更新
     */
    fun submitList(userList: List<UserView>) {
        mdiffer.submitList(userList)
    }

    /**
     * アダプタが保持するデータセット内のアイテムの総数を返します
     * @return このアダプターのアイテムの総数
     */
    override fun getItemCount(): Int {
        return mdiffer.currentList.size
    }

    // ----- クリックリスナー ----------------------------------------
    private var itemClickListener: OnItemClickListener = object: OnItemClickListener {
        override fun onItemClick(holder: ViewHolder) {
            val pos = holder.adapterPosition
            val firstName = holder.firstName.text
            val lastName = holder.lastName.text
            val deptName = holder.deptName.text
            Log.d("MyAdapter", "pos=${pos} firstName=${firstName} lastName=${lastName} deptName=${deptName}")
        }
    }
    interface OnItemClickListener {
        fun onItemClick(holder: ViewHolder)
    }
}