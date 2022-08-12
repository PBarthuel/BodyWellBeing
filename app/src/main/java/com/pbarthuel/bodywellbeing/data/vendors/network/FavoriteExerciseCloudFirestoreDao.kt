package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.app.models.Exercise
import javax.inject.Inject

class FavoriteExerciseCloudFirestoreDao @Inject constructor() {

    private val db = Firebase.firestore

    fun addExerciseToFavorite(userId: String, exercise: Exercise) {
        db.collection(userId)
            .document(exercise.id)
            .set(exercise)
            .addOnSuccessListener {
                Log.d("FavoriteExerciseCloudFirestoreDao", "createExercise: success")
            }
            .addOnFailureListener {
                Log.d("FavoriteExerciseCloudFirestoreDao", "createExercise: Failure" + it.message)
            }
    }

    fun getFavoriteExercisesId(userId: String): List<Exercise> {
        val exercises = ArrayList<Exercise>()
        db.collection(userId)
            .get()
            .addOnSuccessListener {result ->
                for (document in result) {
                    exercises.add(document.toObject())
                }
            }
        return exercises
    }
}