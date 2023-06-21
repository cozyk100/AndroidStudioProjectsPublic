package com.example.room.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.room.db.AppDatabase
import com.example.room.entity.Dept
import com.example.room.entity.User
import com.example.room.entity.UserView
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * UserDaoのテスト
 */
@RunWith(AndroidJUnit4::class)
class UserViewDaoTest {
    /** DB */
    private lateinit var appDatabase: AppDatabase
    /** Dao */
    private lateinit var userDao: UserDao
    private lateinit var deptDao: DeptDao
    private lateinit var userViewDao: UserViewDao

    @Before
    fun creatDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        userDao = appDatabase.userDao()
        deptDao = appDatabase.deptDao()
        userViewDao = appDatabase.userViewDao()
    }

    @After
    @Throws(IOException::class)
    fun cleanup() {
        appDatabase.close()
    }

    /**
     * 4件ヒット
     */
    @Test
    fun userViewDaoTest001() = runTest {
        userDao.insert(User(0, "first1", "last1","dept1"))
        userDao.insert(User(0, "first2", "last2","dept2"))
        userDao.insert(User(0, "first3", "last3","dept3"))
        userDao.insert(User(0, "first9", "last9","dept9"))
        deptDao.insert(Dept("dept1", "deptName1"))
        deptDao.insert(Dept("dept2", "deptName2"))
        deptDao.insert(Dept("dept3", "deptName3"))
        deptDao.insert(Dept("dept5", "deptName5"))
        val rslt = userViewDao.getAll().first()
        assertThat(rslt.size).isEqualTo(4)
        assertThat(rslt[0]).isEqualTo(UserView(1, "first1", "last1","deptName1"))
        assertThat(rslt[1]).isEqualTo(UserView(2, "first2", "last2","deptName2"))
        assertThat(rslt[2]).isEqualTo(UserView(3, "first3", "last3","deptName3"))
        assertThat(rslt[3]).isEqualTo(UserView(4, "first9", "last9",null))
    }
}