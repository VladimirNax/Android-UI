package com.niknax.attachment.DI.modules

import com.niknax.attachment.data.MainRepository
import com.niknax.attachment.data.TmdbApi
import com.niknax.attachment.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}