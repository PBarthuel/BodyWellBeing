package com.pbarthuel.bodywellbeing.app.ui.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.model.CondenseExercise
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic3
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin

@Composable
fun ExerciseCard(
    exercise: CondenseExercise,
    onCardClicked: () -> Unit
) {
    CustomCard(onCardClicked = onCardClicked) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = HorizontalMargin, vertical = Basic2)
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = exercise.getDrawableFromType()),
                    contentDescription = "Exercise icon"
                )
                Spacer(modifier = Modifier.size(Basic3))
                Body1(modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically), text = exercise.name)
                Spacer(modifier = Modifier.size(Basic3))
                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "arrow right"
                )
            }
            if (exercise is CondenseExercise.Custom) {
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                    painter = painterResource(id = R.drawable.ic_custom_exercise_mark),
                    contentDescription = null,
                )
            }
        }
    }
}