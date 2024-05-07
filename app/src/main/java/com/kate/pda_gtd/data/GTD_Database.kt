package com.kate.pda_gtd.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Task::class, Category::class], version = 3, exportSchema = false)
public abstract class GTD_Database : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun categoryDao(): CategoryDao
    companion object {
        @Volatile
        private var INSTANCE: GTD_Database? = null
        fun getDatabase(context: Context) : GTD_Database {

            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    GTD_Database::class.java,
                    "playground_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it } } } } }