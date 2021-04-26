package com.niknax.attachment.DI.modules

import android.content.Context
import com.niknax.attachment.data.MainRepository
import com.niknax.attachment.data.PreferenceProvider
import com.niknax.attachment.data.TmdbApi
import com.niknax.attachment.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
//Передаем контекст для SharedPreferences через конструктор
class DomainModule(val context: Context) {
    //Нам нужно контекст как-то провайдить, поэтому создаем такой метод
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    //Создаем экземпляр SharedPreferences
    fun providePreferences(context: Context) = PreferenceProvider(context)

    @Singleton
    @Provides
    fun provideInteractor(
        repository: MainRepository,
        tmdbApi: TmdbApi,
        preferenceProvider: PreferenceProvider
    ) = Interactor(repo = repository, retrofitService = tmdbApi, preferences = preferenceProvider)
}