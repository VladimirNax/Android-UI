package com.niknax.attachment.domain

import com.niknax.attachment.API
import com.niknax.attachment.data.Entity.Film
import com.niknax.attachment.data.TmdbApi
import com.niknax.attachment.data.Entity.TmdbResultsDto
import com.niknax.attachment.data.MainRepository
import com.niknax.attachment.data.PreferenceProvider
import com.niknax.attachment.utils.Converter
import com.niknax.attachment.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(
    private val repo: MainRepository,
    private val retrofitService: TmdbApi,
    private val preferences: PreferenceProvider
) {
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page)
            .enqueue(object : Callback<TmdbResultsDto> {
                override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                    //При успехе мы вызываем метод, передаем onSuccess и в этот коллбэк список фильмов
                    val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                    //Кладем фильмы в бд
                    list.forEach {
                        repo.putToDb(list)
                    }
                    callback.onSuccess(list)
                }

                override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                    //В случае провала вызываем другой метод коллбека
                    callback.onFailure()
                }
            })
    }

    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.geDefaultCategory()

    //Метод дергает метод репозитория MainRepository , чтобы тот забрал фильмы из БД
    fun getFilmsFromDB(): List<Film> = repo.getAllFromDB()
}