package ru.dmdev.coronavirusapiapp.di.components

import dagger.Component
import ru.dmdev.coronavirusapiapp.MainActivity
import ru.dmdev.coronavirusapiapp.di.modules.*
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    MainActivityModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
}
