package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ExerciseCloudFirestoreDao @Inject constructor() {

    private val db = Firebase.firestore.collection("exercise")

    fun createExercise(exercise: Exercise) {
        db.document(exercise.id)
            .set(exercise)
            .addOnSuccessListener {
                Log.d("ExerciseCloudFirestoreDao", "createExercise: success")
            }
            .addOnFailureListener {
                Log.d("ExerciseCloudFirestoreDao", "createExercise: Failure" + it.message)
            }
    }

    // TODO passer en flow et callBackflow peut-être
    fun getExercise(exerciseId: String): Exercise {
        var exercise = Exercise(
            id = "developpéCouché1",
            name = "Developpé couché",
            description = "Developpé couché",
            type = ExercisesConstants.CHEST_EXERCISE_TYPE
        )
        db.document(exerciseId)
            .get()
            .addOnSuccessListener {
                exercise = it.toObject<Exercise>()!!
            }
        return exercise
    }
}