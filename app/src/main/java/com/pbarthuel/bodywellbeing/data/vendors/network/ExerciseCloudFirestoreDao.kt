package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

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

    fun getAllExercises(): Flow<List<Exercise>> = callbackFlow {
        db.addSnapshotListener { value, error ->
            if (error != null) {
                Log.d("ExerciseCloudFirestoreDao", "Listen failed ")
            }

            if (value != null && !value.isEmpty) {
                trySend(value.documents.map {
                    it.toObject<Exercise>()!!
                })
            } else {
                Log.d("ExerciseCloudFirestoreDao", "Fail to retrieving data ")
            }
        }
        awaitClose { cancel() }
    }
}