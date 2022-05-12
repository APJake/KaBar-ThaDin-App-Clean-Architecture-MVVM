package com.apjake.kabarthadin.presentation.navigation

sealed class Screen(val route: String) {
    object ArticleList: Screen("article_list_screen")
    object ArticleDetail: Screen("article_detail_screen")
    object WebView: Screen("web_view")
}