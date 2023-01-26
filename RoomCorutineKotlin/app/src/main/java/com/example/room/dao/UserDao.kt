package com.example.room.dao

import androidx.room.*
import com.example.room.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * UserのDao
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: User)
}