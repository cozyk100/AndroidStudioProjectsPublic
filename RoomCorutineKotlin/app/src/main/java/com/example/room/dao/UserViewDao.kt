package com.example.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.room.entity.UserView
import kotlinx.coroutines.flow.Flow

/**
 * User_viewのDao
 */
@Dao
interface UserViewDao {
    @Query("SELECT * FROM User_view")
    fun getAll(): Flow<List<UserView>>
}