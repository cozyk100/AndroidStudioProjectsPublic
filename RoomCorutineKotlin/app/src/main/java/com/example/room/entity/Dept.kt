package com.example.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 所属
 */
@Entity(tableName = "Dept")
data class Dept(
    /** PKey 所属コード */
    @PrimaryKey @ColumnInfo(name = "dept_cd") val deptCd: String,
    /** 所属名 */
    @ColumnInfo(name = "dept_name") val deptName: String?
)
