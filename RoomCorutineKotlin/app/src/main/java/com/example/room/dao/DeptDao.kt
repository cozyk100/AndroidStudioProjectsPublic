package com.example.room.dao

import androidx.room.*
import com.example.room.entity.Dept
import kotlinx.coroutines.flow.Flow

/**
 * DeptのDao
 */
@Dao
interface DeptDao {
    @Query("SELECT * FROM dept")
    fun getAll(): Flow<List<Dept>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dept: Dept)
}