package ru.dmdev.coronavirusapiapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dmdev.coronavirusapiapp.models.Country
import ru.dmdev.coronavirusapiapp.repositories.Covid19RepositoryImpl
import ru.dmdev.coronavirusapiapp.repositories.models.RepositoryResult

class MainViewModel() : ViewModel() {
    private val covid19Repository = Covid19RepositoryImpl()

    private val _spinner = MutableLiveData<Boolean>()
    val spinner : LiveData<Boolean> get() = _spinner

    private val _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>> get() = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun refreshCountries() {
        viewModelScope.launch {
            _spinner.postValue(true)
            when (val result = covid19Repository.getCovid19Statistics()) {
                is RepositoryResult.Success -> _countries.postValue(result.data)
                is RepositoryResult.Error -> _error.postValue(result.exception.message)
            }
            _spinner.postValue(false)
        }
    }
}