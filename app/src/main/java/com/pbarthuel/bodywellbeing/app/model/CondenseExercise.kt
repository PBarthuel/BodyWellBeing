package com.pbarthuel.bodywellbeing.app.model

import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants

sealed class CondenseExercise(
    open val id: String,
    open val name: String,
    open val isFavorite: Boolean = false,
    open val type: Int
) {
    data class Classic(
        override val id: String,
        override val name: String,
        override val isFavorite: Boolean = false,
        override val type: Int
    ) : CondenseExercise(
        id = id,
        name = name,
        isFavorite = isFavorite,
        type = type
    )

    data class Custom(
        override val id: String,
        override val name: String,
        override val isFavorite: Boolean= false,
        override val type: Int
    ) : CondenseExercise(
        id = id,
        name = name,
        isFavorite = isFavorite,
        type = type
    )

    fun getTitleFromType() =
        when(type) {
            ExercisesConstants.ARM_EXERCISE_TYPE -> R.string.arm_exercises
            ExercisesConstants.TRICEPS_EXERCISE_TYPE -> R.string.triceps_exercises
            ExercisesConstants.BACK_EXERCISE_TYPE -> R.string.back_exercises
            ExercisesConstants.SHOULDER_EXERCISE_TYPE -> R.string.shoulder_exercises
            ExercisesConstants.CHEST_EXERCISE_TYPE -> R.string.chest_exercises
            ExercisesConstants.ABS_EXERCISE_TYPE -> R.string.abs_exercises
            ExercisesConstants.LEG_EXERCISE_TYPE -> R.string.leg_exercises
            else -> R.string.error_exercises
        }

    fun getDrawableFromType(): Int {
        return when (type) {
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
}