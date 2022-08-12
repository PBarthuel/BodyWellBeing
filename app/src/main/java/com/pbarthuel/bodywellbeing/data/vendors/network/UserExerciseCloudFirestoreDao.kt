package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.app.models.Exercise
import javax.inject.Inject

class UserExerciseCloudFirestoreDao @Inject constructor() {

    companion object {
        const val FAVORITE_COLLECTION = "favorite"
        const val CUSTOM_EXERCISE_COLLECTION = "customExercise"
    }

    private val db = Firebase.firestore.collection("userExercises")

    fun addExerciseToFavorite(userId: String, exercise: Exercise) {
        db.document(userId)
            .collection(FAVORITE_COLLECTION)
            .document(exercise.id)
            .set(exercise)
            .addOnSuccessListener {
                Log.d("UserExerciseCloudFirestoreDao", "addExerciseToFavorite: success")
            }
            .addOnFailureListener {
                Log.d("UserExerciseCloudFirestoreDao", "addExerciseToFavorite: Failure" + it.message)
            }
    }

    fun getFavoriteExercises(userId: String): List<Exercise> {
        val exercises = ArrayList<Exercise>()
        db.document(userId)
            .collection(FAVORITE_COLLECTION)
            .get()
            .addOnSuccessListener {result ->
                for (document in result) {
                    exercises.add(document.toObject())
                }
            }
        return exercises
    }

    fun createCustomExercise(userId: String, exercise: Exercise) {
        db.document(userId)
            .collection(CUSTOM_EXERCISE_COLLECTION)
            .document(exercise.id)
            .set(exercise)
            .addOnSuccessListener {
                Log.d("UserExerciseCloudFirestoreDao", "createCustomExercise: success")
            }
            .addOnFailureListener {
                Log.d("UserExerciseCloudFirestoreDao", "createCustomExercise: Failure" + it.message)
            }
    }

    fun getCustomExercises(userId: String): List<Exercise> {
        val exercises = ArrayList<Exercise>()
        db.document(userId)
            .collection(CUSTOM_EXERCISE_COLLECTION)
            .get()
            .addOnSuccessListener {result ->
                for (document in result) {
                    exercises.add(document.toObject())
                }
            }
        return exercises
    }
}