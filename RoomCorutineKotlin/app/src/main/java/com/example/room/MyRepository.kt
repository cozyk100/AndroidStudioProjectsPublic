package com.example.room

import android.content.Context
import androidx.annotation.WorkerThread
import com.example.room.db.AppDatabase
import com.example.room.entity.Dept
import com.example.room.entity.User
import com.example.room.entity.UserView
import kotlinx.coroutines.flow.Flow

class MyRepository(context: Context) {
    // Room
    private val db = AppDatabase.getDatabase(context)
    private val userDao = db.userDao()
    private val deptDao = db.deptDao()
    private val userViewDao = db.userViewDao()

    /** User */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    /** Dept */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertDept(dept: Dept) {
        deptDao.insert(dept)
    }

    /** UserView */
    val userViewList: Flow<List<UserView>> = userViewDao.getAll()
}