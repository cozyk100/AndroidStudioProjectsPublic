package com.example.recyclerviewandvisiblekotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewandvisiblekotlin.databinding.MyTextViewBinding

class MyAdapter(private val data: List<List<String>>): RecyclerView.Adapter<MyAdapter.ViewHolder>()  {

    class ViewHolder(view: View):  RecyclerView.ViewHolder(view) {
        private var binding: MyTextViewBinding
        var list1: TextView
        var list2: TextView
        var list3: TextView

        init {
            binding = MyTextViewBinding.bind(view)
            list1 = binding.list1
            list2 = binding.list2
            list3 = binding.list3
        }

        fun bindTo(data: List<String>) {
            list1.text = data.get(0)
            list2.text = data.get(1)
            list3.text = data.get(2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MyTextViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.list1.text = data[position][0]
        holder.list2.text = data[position][1]
        holder.list3.text = data[position][2]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}