package com.niknax.attachment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niknax.attachment.App
import com.niknax.attachment.data.Entity.Film
import com.niknax.attachment.domain.Interactor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject


import java.util.concurrent.Executors
import javax.inject.Inject


class HomeFragmentViewModel : ViewModel() {

    val showProgressBar: BehaviorSubject<Boolean>

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    val filmsListData: Observable<List<Film>>


    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        /*showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }

            override fun onFailure() {
                showProgressBar.postValue(false)
            }
        })*/
        interactor.getFilmsFromApi(1)
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }


}