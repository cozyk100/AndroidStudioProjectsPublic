package com.example.recyclerviewandvisiblekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewandvisiblekotlin.databinding.ActivityMainBinding

/**
 * RecyclerViewを挟んで上下にTextViewを配置
 * TextViewをGroupに入れて、GroupのvisibilityをVISIBLE/GONEに変化させた場合
 * TextViewを上側、下側に２個、バリアを設定して、１個めのTextViewのvisibilityVISIBLE/GONEに変化させた場合
 * のサンプル
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val data:List<List<String>> = listOf(
        listOf("ああああ", "いいいい", "うううう"),
        listOf("ああああ", "いいいい", "うううう"),
        listOf("ああああ", "いいいい", "うううう"),
        listOf("ああああ", "いいいい", "うううう"),
        listOf("ああああ", "いいいい", "うううう"),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("いいいい", "うううう", "ああああ" ),
        listOf("いいいい", "うううう", "ああああ" ),
        listOf("いいいい", "うううう", "ああああ" ),
        listOf("いいいい", "うううう", "ああああ" ),
        listOf("いいいい", "うううう", "ああああ" ),
        listOf("ああああ", "いいいい", "うううう"),
        listOf("ああああ", "いいいい", "うううう"),
        listOf("ああああ", "いいいい", "うううう"),
        listOf("ああああ", "いいいい", "うううう"),
        listOf("ああああ", "いいいい", "うううう"),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("うううう", "ああああ", "いいいい" ),
        listOf("いいいい", "うううう", "ああああ" ),
        listOf("いいいい", "うううう", "ああああ" ),
        listOf("いいいい", "うううう", "ああああ" ),
        listOf("いいいい", "うううう", "ああああ" ),
        listOf("いいいい", "うううう", "ああああ" ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val myAdapter = MyAdapter(data)
        recyclerView.adapter = myAdapter
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration) // 区切り線

        binding.button.setOnClickListener {
            if (binding.group1.visibility == View.GONE) {
                binding.group1.visibility = View.VISIBLE
            } else {
                binding.group1.visibility = View.GONE
            }
        }
        binding.button2.setOnClickListener {
            if (binding.group2.visibility == View.GONE) {
                binding.group2.visibility = View.VISIBLE
            } else {
                binding.group2.visibility = View.GONE
            }
        }
        binding.button3.setOnClickListener {
            if (binding.textUp1.visibility == View.GONE) {
                binding.textUp1.visibility = View.VISIBLE
            } else {
                binding.textUp1.visibility = View.GONE
            }
        }
        binding.button4.setOnClickListener {
            if (binding.textDown1.visibility == View.GONE) {
                binding.textDown1.visibility = View.VISIBLE
            } else {
                binding.textDown1.visibility = View.GONE
            }
        }
    }
}