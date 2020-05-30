package ru.dmdev.coronavirusapiapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dmdev.coronavirusapiapp.models.Country
import ru.dmdev.coronavirusapiapp.repositories.Covid19Repository
import ru.dmdev.coronavirusapiapp.repositories.models.RepositoryResult
import javax.inject.Inject

class MainViewModel @Inject constructor(private val covid19Repository: Covid19Repository) : ViewModel() {

    private val _spinner = MutableLiveData<Boolean>()
    val spinner : LiveData<Boolean> get() = _spinner

    private val _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>> get() = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun refreshCountries() {
        if (_countries.value?.isEmpty() == false)
            return

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
