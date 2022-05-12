package com.apjake.kabarthadin.presentation.mvvm.pagination

interface Pagination<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}