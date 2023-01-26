package com.example.room.entity

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Entity

/**
 * ユーザView
 */
@DatabaseView( viewName ="User_view",
    value = """
    select user.uid, user.first_name, user.last_name, dept.dept_name from user 
        left outer join dept on (user.dept_cd = dept.dept_cd)
""")
@Entity(tableName = "User_view")
data class UserView(
    /** uid(PKey) */
    @ColumnInfo(name = "uid") val uid: Int,
    /** ファーストネーム */
    @ColumnInfo(name = "first_name") val firstName: String?,
    /** ラストネーム */
    @ColumnInfo(name = "last_name") val lastName: String?,
    /** 所属名 */
    @ColumnInfo(name = "dept_name") val deptName: String?
)
