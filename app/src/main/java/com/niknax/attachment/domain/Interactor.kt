package com.niknax.attachment.domain

import com.niknax.attachment.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}