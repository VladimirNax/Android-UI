package com.niknax.attachment.data

import android.content.ContentValues
import android.database.Cursor
import com.niknax.attachment.data.DAO.FilmDao
import com.niknax.attachment.data.db.DatabaseHelper
import com.niknax.attachment.data.Entity.Film
import java.util.concurrent.Executors


class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): List<Film> {
        return filmDao.getCachedFilms()
    }
}