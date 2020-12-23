package com.deazzle.roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deazzle.roomdatabase.dao.CustomerDao
import com.deazzle.roomdatabase.entities.UsersData

/**
 * Created by pratikg on 2/8/18.
 */
@Database(entities = [UsersData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao?

    companion object {
        private var instance: AppDatabase? = null
        fun getAppDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "users-db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}