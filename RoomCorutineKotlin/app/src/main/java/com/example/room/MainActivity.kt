package com.example.room

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.databinding.ActivityMainBinding
import com.example.room.entity.Dept
import com.example.room.entity.User

/**
 * Room/SQLiteのサンプル、Coroutine版
 * https://akira-watson.com/android/sqlite.html
 * https://developer.android.com/training/data-storage/room?hl=ja
 * https://developer.android.com/jetpack/androidx/releases/room?hl=ja
 * https://developer.android.com/topic/libraries/architecture/room?hl=ja
 * https://qiita.com/morayl/items/dd62114dcb8cd7edb509
 * https://pg.akihiro-takeda.com/android-room-rxjava/#toc23
 */
class MainActivity : AppCompatActivity() {
    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding
    /** ViewModel */
    private val myViewModel: MyViewModel by viewModels {
        MyViewModelFactory(MyRepository(applicationContext))
    }

    /**
     * メインアクテビティ
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // RecyclerView
        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val myAdapter = MyAdapter()
        recyclerView.adapter = myAdapter
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration) // 区切り線

        // 画面の項目
        val editTextFirstName = binding.editTextFirstName
        val editTextLastName = binding.editTextLastName
        val editTextUserDept = binding.editTextUserDept
        val editTextDeptCd = binding.editTextDeptCd
        val editTextDeptName = binding.editTextDeptName
        val userAddBtn = binding.userAddBtn
        val deptAddBtn = binding.deptAddBtn

        // ユーザ追加ボタン
        userAddBtn.setOnClickListener {
            val firstName = editTextFirstName.text.toString()
            val lastName = editTextLastName.text.toString()
            val depotCd = editTextUserDept.text.toString()
            val user = User(0, firstName, lastName, depotCd)
            myViewModel.insertUser(user)
        }
        // 部署追加ボタン
        deptAddBtn.setOnClickListener {
            val deptCd = editTextDeptCd.text.toString()
            val deptName = editTextDeptName.text.toString()
            val dept = Dept(deptCd, deptName)
            myViewModel.insertDept(dept)
        }
        // RecyclerViewの再表示
        myViewModel.userViewList.observe(this) { userViewList ->
            userViewList?.let { myAdapter.submitList(it) }
        }
    }

    /**
     * アクティビティの破棄
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy() が呼ばれました")
    }
}