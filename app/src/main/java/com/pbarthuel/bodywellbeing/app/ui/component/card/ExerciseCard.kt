package com.pbarthuel.bodywellbeing.app.ui.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic3
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants

@Composable
fun ExerciseCard(
    exerciseType: Int?,
    exerciseName: String,
    onCardClicked: () -> Unit
) {
    CustomCard(onCardClicked = onCardClicked) {
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
                painter = painterResource(id = getDrawableFromType(exerciseType)),
                contentDescription = "Exercise icon"
            )
            Spacer(modifier = Modifier.size(Basic3))
            Body1(modifier = Modifier.weight(1f).align(Alignment.CenterVertically), text = exerciseName)
            Spacer(modifier = Modifier.size(Basic3))
            Image(
                modifier = Modifier.size(30.dp).align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "arrow right"
            )
        }
    }
}

private fun getDrawableFromType(exerciseType: Int?): Int {
    return when (exerciseType) {
        ExercisesConstants.ARM_EXERCISE_TYPE -> R.drawable.ic_stock_eye
        ExercisesConstants.TRICEPS_EXERCISE_TYPE -> R.drawable.ic_launcher_foreground
        ExercisesConstants.BACK_EXERCISE_TYPE -> R.drawable.ic_launcher_background
        ExercisesConstants.SHOULDER_EXERCISE_TYPE -> R.drawable.ic_stock_eye2
        ExercisesConstants.CHEST_EXERCISE_TYPE -> R.drawable.ic_google_logo
        ExercisesConstants.ABS_EXERCISE_TYPE -> R.drawable.ic_google_logo
        ExercisesConstants.LEG_EXERCISE_TYPE -> R.drawable.ic_google_logo
        else -> R.drawable.ic_google_logo
    }
}