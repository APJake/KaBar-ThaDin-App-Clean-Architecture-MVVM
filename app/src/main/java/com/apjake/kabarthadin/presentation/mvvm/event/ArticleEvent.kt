package com.apjake.kabarthadin.presentation.mvvm.event

sealed class ArticleEvent {
    data class ShowSnackBar(val message: String): ArticleEvent()
}