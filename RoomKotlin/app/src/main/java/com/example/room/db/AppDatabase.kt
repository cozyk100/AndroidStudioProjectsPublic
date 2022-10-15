package com.example.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.dao.DeptDao
import com.example.room.dao.UserDao
import com.example.room.dao.UserViewDao
import com.example.room.entity.Dept
import com.example.room.entity.User
import com.example.room.entity.UserView

/**
 * データベース
 */
@Database(entities = [User::class, Dept::class], views = [UserView::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "room-sample.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null
        // Database
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME)
                    // .addMigrations(MIGRATION_1_2, MIGRATION_2_3) // Migratioの例
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Migratioの例
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                        "PRIMARY KEY(`id`))")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")
            }
        }
    }

    // Dao
    abstract fun userDao(): UserDao
    abstract fun deptDao(): DeptDao
    abstract fun userViewDao(): UserViewDao
}