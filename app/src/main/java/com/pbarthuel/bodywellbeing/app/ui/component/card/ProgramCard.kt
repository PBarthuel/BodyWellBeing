package com.pbarthuel.bodywellbeing.app.ui.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pbarthuel.bodywellbeing.app.models.program.ProgramPreview
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header4
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin

@ExperimentalMaterialApi
@Composable
fun ProgramCard(
    programPreview: ProgramPreview,
    onCardClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .defaultMinSize(
                minHeight = 128.dp
            )
            .fillMaxWidth(),
        elevation = 0.dp,
        onClick = onCardClicked,
    ) {
        Box {
            Row(
                modifier = Modifier
                    .height(
                        intrinsicSize = IntrinsicSize.Max
                    ),
                verticalAlignment = CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .width(
                            width = 128.dp
                        )
                        .fillMaxHeight()
                        .defaultMinSize(
                            minHeight = 128.dp
                        ),
                    painter = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(
                                context = LocalContext.current
                            )
                            .data(
                                data = programPreview.thumbnail,
                            )
                            .build(),
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Header4(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = HorizontalMargin,
                            vertical = Basic2,
                        ),
                    text = programPreview.title,
                )
            }
        }
    }
}