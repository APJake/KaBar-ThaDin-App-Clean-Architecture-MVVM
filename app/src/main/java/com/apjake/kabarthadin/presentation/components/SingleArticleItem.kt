package com.apjake.kabarthadin.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.apjake.kabarthadin.domain.model.Article

import com.apjake.kabarthadin.R
import com.apjake.kabarthadin.common.util.toFormattedDate


@Composable
fun SingleArticleItem(
    article: Article,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier, elevation = 5.dp, shape = RoundedCornerShape(10.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
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
            Text(
                text = article.publishedAt.toFormattedDate(),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = article.content,
                style = MaterialTheme.typography.body1,
            )

        }
    }
}