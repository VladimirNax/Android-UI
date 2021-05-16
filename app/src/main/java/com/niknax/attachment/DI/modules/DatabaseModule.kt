package com.niknax.attachment.DI.modules

import android.content.Context
import androidx.room.Room
import com.niknax.attachment.data.DAO.FilmDao
import com.niknax.attachment.data.MainRepository
import com.niknax.attachment.data.db.AppDatabase
import com.niknax.attachment.data.db.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideFilmDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}