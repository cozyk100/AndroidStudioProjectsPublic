package com.example.room.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.room.db.AppDatabase
import com.example.room.entity.User
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
class UserDaoTest {
    /** DB */
    private lateinit var appDatabase: AppDatabase
    /** Dao */
    private lateinit var userDao: UserDao

    @Before
    fun creatDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        userDao = appDatabase.userDao()
    }

    @After
    @Throws(IOException::class)
    fun cleanup() {
        appDatabase.close()
    }

    /**
     * 0件
     */
    @Test
    fun userDaoTest001() = runTest {
        val rslt = userDao.getAll()
        assertThat(rslt.first()).isEmpty()
    }

    /**
     * 3件
     */
    @Test
    fun userDaoTest002() = runTest {
        userDao.insert(User(0, "first1", "last1","dept1"))
        userDao.insert(User(0, "first2", "last2","dept2"))
        userDao.insert(User(0, "first3", "last3","dept3"))
        val rslt = userDao.getAll().first()
        assertThat(rslt.size).isEqualTo(3)
        assertThat(rslt[0]).isEqualTo(User(1, "first1", "last1","dept1"))
        assertThat(rslt[1]).isEqualTo(User(2, "first2", "last2","dept2"))
        assertThat(rslt[2]).isEqualTo(User(3, "first3", "last3","dept3"))
    }
}