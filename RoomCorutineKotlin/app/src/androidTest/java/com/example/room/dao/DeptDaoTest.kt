package com.example.room.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.room.db.AppDatabase
import com.example.room.entity.Dept
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * DeptDaoのテスト
 */
@RunWith(AndroidJUnit4::class)
class DeptDaoTest {
    /** DB */
    private lateinit var appDatabase: AppDatabase
    /** Dao */
    private lateinit var deptDao: DeptDao

    @Before
    fun creatDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        deptDao = appDatabase.deptDao()
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
    fun deptDaoTest001() = runTest {
        val rslt = deptDao.getAll()
        assertThat(rslt.first()).isEmpty()
    }

    /**
     * 3件
     */
    @Test
    fun deptDaoTest002() = runTest {
        deptDao.insert(Dept("dept1", "deptName1"))
        deptDao.insert(Dept("dept2", "deptName2"))
        deptDao.insert(Dept("dept3", "deptName3"))
        val rslt = deptDao.getAll().first()
        assertThat(rslt.size).isEqualTo(3)
        assertThat(rslt[0]).isEqualTo(Dept("dept1", "deptName1"))
        assertThat(rslt[1]).isEqualTo(Dept("dept2", "deptName2"))
        assertThat(rslt[2]).isEqualTo(Dept("dept3", "deptName3"))
    }
}