package com.niknax.attachment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niknax.attachment.App
import com.niknax.attachment.domain.Film
import com.niknax.attachment.domain.Interactor
import javax.inject.Inject


class HomeFragmentViewModel : ViewModel() {

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    val filmsListLiveData:  MutableLiveData<List<Film>> = MutableLiveData()


    init {

        App.instance.dagger.inject(this)

        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                filmsListLiveData.postValue(films)
            }

            override fun onFailure() {
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }
}