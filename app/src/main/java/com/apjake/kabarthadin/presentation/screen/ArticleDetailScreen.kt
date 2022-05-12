package com.apjake.kabarthadin.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.apjake.kabarthadin.R
import com.apjake.kabarthadin.common.util.toFormattedDate
import com.apjake.kabarthadin.domain.model.Article
import com.apjake.kabarthadin.presentation.navigation.Screen

@Composable
fun ArticleDetailScreen(
    article: Article,
    navController: NavHostController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(article.urlToImage)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = article.author,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = article.publishedAt.toFormattedDate(),
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = "by ${article.author}",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = article.description,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    if(article.url.isNotBlank()) Button(onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set("url", article.url)
                        navController.navigate(Screen.WebView.route)
                    }) {
                        Text(text = "READ MORE")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}