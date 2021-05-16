package com.niknax.attachment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niknax.attachment.data.DAO.FilmDao
import com.niknax.attachment.data.Entity.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}