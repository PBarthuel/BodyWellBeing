package com.pbarthuel.bodywellbeing.app.ui.component.button

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.model.Exercise

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    exercise: State<Exercise?>,
    onFavoriteClicked: () -> Unit
) {
    Column(modifier = modifier.wrapContentSize()) {
        val extendedSize = 60.dp
        val normalSize = 40.dp

        var heartIcon by remember { mutableStateOf(R.drawable.ic_heart_grey) }
        heartIcon = when (exercise.value?.isFavorite) {
            true -> R.drawable.ic_heart_red
            false -> R.drawable.ic_heart_grey
            else -> R.drawable.ic_heart_grey
        }

        val selected = remember { mutableStateOf(false) }
        val state = animateDpAsState(targetValue = if (selected.value) extendedSize else normalSize)
        if (state.value == extendedSize) { selected.value = false }

        Image(
            modifier = Modifier
                .size(state.value)
                .clickable {
                    selected.value = true
                    onFavoriteClicked()
                },
            painter = painterResource(id = heartIcon),
            contentDescription = "fav"
        )
    }
}