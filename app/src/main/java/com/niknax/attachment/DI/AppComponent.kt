package com.niknax.attachment.DI

import com.niknax.attachment.DI.modules.DatabaseModule
import com.niknax.attachment.DI.modules.DomainModule
import com.niknax.attachment.DI.modules.RemoteModule
import com.niknax.attachment.viewmodel.HomeFragmentViewModel
import com.niknax.attachment.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    //метод для того, чтобы появилась возможность внедрять зависимости в SettingsFragmentViewModel
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}