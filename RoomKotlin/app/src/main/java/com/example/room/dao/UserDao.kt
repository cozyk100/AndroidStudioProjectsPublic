package com.example.room.dao

import androidx.room.*
import com.example.room.entity.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * UserのDao
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flowable<List<User>>

    @Query("SELECT * FROM user where uid = :uid")
    fun getByPk(uid: Int): Single<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: User): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(usersList: List<User>): Completable

    @Delete
    fun delete(user: User): Completable
}