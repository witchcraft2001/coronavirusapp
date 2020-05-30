package ru.dmdev.coronavirusapiapp.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.dmdev.coronavirusapiapp.viewmodels.MainViewModel
import ru.dmdev.coronavirusapiapp.di.ViewModelKey

@Module
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}