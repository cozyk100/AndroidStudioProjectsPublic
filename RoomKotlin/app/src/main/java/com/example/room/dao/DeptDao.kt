package com.example.room.dao

import androidx.room.*
import com.example.room.entity.Dept
import com.example.room.entity.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * DeptのDao
 */
@Dao
interface DeptDao {
    @Query("SELECT * FROM dept")
    fun getAll(): Flowable<List<Dept>>

    @Query("SELECT * FROM dept where dept_cd = :deptCd")
    fun getByPk(deptCd: String): Single<Dept>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dept: Dept): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(deptList: List<Dept>): Completable

    @Delete
    fun delete(dept: Dept): Completable
}