package com.dznow.project.device

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dznow.project.data.entity.UserEntity
import com.dznow.project.presentation.model.Article

@Database(entities = [UserEntity::class,Article::class], version = 1)
abstract class DzNowDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: DzNowDatabase? = null
        fun getDatabase(context: Context): DzNowDatabase? {
            if (INSTANCE == null) {
                synchronized(DzNowDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DzNowDatabase::class.java, "dznow.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

}