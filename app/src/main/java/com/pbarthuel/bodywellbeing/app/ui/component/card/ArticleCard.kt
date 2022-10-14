package com.pbarthuel.bodywellbeing.app.ui.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.model.article.Article
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic3
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin

@Composable
fun ArticleCard(
    article: Article,
    onCardClicked: () -> Unit
) {
    CustomCard(onCardClicked = onCardClicked) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .width(
                            width = 64.dp
                        )
                        .fillMaxHeight()
                        .defaultMinSize(
                            minHeight = 64.dp
                        ),
                    painter = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(context = LocalContext.current)
                            .data(
                                data = "https://st.depositphotos.com/1146092/4777/i/600/depositphotos_47770061-stock-photo-cool-dog.jpg",
                            )
                            .build(),
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.size(Basic3))
                Body1(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = HorizontalMargin)
                        .align(Alignment.CenterVertically),
                    text = article.title
                )
                Spacer(modifier = Modifier.size(Basic3))
                Image(
                    modifier = Modifier
                        .padding(end = HorizontalMargin)
                        .size(30.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "arrow right"
                )
            }
        }
    }
}