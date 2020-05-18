package ru.dmdev.coronavirusapiapp.repositories.exceptions

import java.lang.Exception

class RepositoryDataNotFoundException(message: String) : Exception(message) { }
