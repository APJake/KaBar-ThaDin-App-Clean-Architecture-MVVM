package com.apjake.kabarthadin.presentation.screen

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.apjake.kabarthadin.presentation.components.SingleArticleItem
import com.apjake.kabarthadin.presentation.mvvm.event.ArticleEvent
import com.apjake.kabarthadin.presentation.mvvm.viewmodel.ArticleListViewModel
import com.apjake.kabarthadin.presentation.navigation.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ArticleListScreen(
    navController: NavHostController
){

    val viewModel: ArticleListViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        viewModel.articlesEvent.collectLatest { event ->
            when(event){
                is ArticleEvent.ShowSnackBar ->{
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                OutlinedTextField(
                    value = viewModel.searchQuery.value,
                    onValueChange = viewModel::search,
                    modifier = Modifier
                        .weight(1f)
                        .background(MaterialTheme.colors.background),
                    shape = RoundedCornerShape(30.dp),
                    placeholder = {
                        Text(text = "Bitcoin")
                    },
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
                onRefresh = { viewModel.reload() },
                modifier = Modifier.fillMaxSize()
            ) {
                val articles = state.articles
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ){
                    items(articles.size){ i->
                        if (i >= state.articles.size - 2 && !state.endReached && !state.isLoading && !state.isLoadingMore) {
                            viewModel.loadMore()
                        }

                        val article = articles[i]
                        SingleArticleItem(
                            article = article,
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                                .clickable {
                                    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                                    navController.navigate(Screen.ArticleDetail.route)
                                }
                        )
                    }
                    item {
                        if(state.isLoadingMore) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                    }
                }
            }

        }
    }
}