package com.example.ticktap.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ticktap.data.model.Deadline
import com.example.ticktap.data.model.Note
import com.example.ticktap.data.model.Task

@Database(entities = [Task::class, Note::class, Deadline::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun noteDao(): NoteDao
    abstract fun deadlineDao(): DeadlineDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ticktap_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
