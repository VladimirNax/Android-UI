package com.niknax.attachment.data

import androidx.lifecycle.LiveData
import com.niknax.attachment.data.DAO.FilmDao
import com.niknax.attachment.data.Entity.Film
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors


class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): Flow<List<Film>> = filmDao.getCachedFilms()
}