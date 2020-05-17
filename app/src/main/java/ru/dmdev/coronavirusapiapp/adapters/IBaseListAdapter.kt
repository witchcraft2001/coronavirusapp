package ru.dmdev.coronavirusapiapp.adapters

interface IBaseListAdapter<T> {
    fun add(newItem: T)
    fun add(newItems: List<T>?)
    fun addAtPosition(position: Int, newItem: T)
    fun remove(position: Int)
    fun clearAll()
}
