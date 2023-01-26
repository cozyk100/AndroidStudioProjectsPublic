package com.example.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ユーザ
 */
@Entity(tableName = "User")
data class User(
    /** PKey */
    @PrimaryKey(autoGenerate = true) val uid: Int,
    /** ファーストネーム */
    @ColumnInfo(name = "first_name") val firstName: String?,
    /** ラストネーム */
    @ColumnInfo(name = "last_name") val lastName: String?,
    /** 所属コード */
    @ColumnInfo(name = "dept_cd") val deptCd: String?
)