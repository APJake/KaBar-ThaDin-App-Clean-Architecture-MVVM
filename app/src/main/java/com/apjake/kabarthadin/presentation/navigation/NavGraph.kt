package com.apjake.kabarthadin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.apjake.kabarthadin.domain.model.Article
import com.apjake.kabarthadin.presentation.screen.ArticleDetailScreen
import com.apjake.kabarthadin.presentation.screen.ArticleListScreen
import com.apjake.kabarthadin.presentation.screen.WebViewScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.ArticleList.route
    ){
        composable(route = Screen.ArticleList.route){
            ArticleListScreen(navController)
        }
        composable(
            route = Screen.ArticleDetail.route,
        ){
            val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
            article?.let {
                ArticleDetailScreen(it, navController)
            }
        }
        composable(
            route = Screen.WebView.route
        ){
            val url = navController.previousBackStackEntry?.savedStateHandle?.get<String>("url")
            url?.let {
                WebViewScreen(it)
            }
        }
    }
}
