package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.data.model.WsExercise
import javax.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserExerciseCloudFirestoreDao @Inject constructor() {

    companion object {
        const val FAVORITE_COLLECTION = "favorite"
        const val CUSTOM_EXERCISE_COLLECTION = "customExercise"
        const val USER_EXERCISES_COLLECTION = "userExercises"
    }

    private val db = Firebase.firestore

    fun addExerciseToFavorite(userId: String, exercise: WsExercise) {
        db.collection(USER_EXERCISES_COLLECTION)
            .document(userId)
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

    fun getAllFavoriteExercises(userId: String): Flow<List<WsExercise>> = callbackFlow {
        db.collection(USER_EXERCISES_COLLECTION)
            .document(userId)
            .collection(FAVORITE_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                trySend(result.documents.map {
                    it.toObject()!!
                })
            }
        awaitClose { close() }
    }

    fun deleteExerciseFromFavorite(userId: String, exerciseId: String) {
        db.collection(USER_EXERCISES_COLLECTION)
            .document(userId)
            .collection(FAVORITE_COLLECTION)
            .document(exerciseId)
            .delete()
            .addOnSuccessListener {
                Log.d("UserExerciseCloudFirestoreDao", "deleteExerciseFromFavorite: success")
            }
            .addOnFailureListener {
                Log.d("UserExerciseCloudFirestoreDao", "deleteExerciseFromFavorite: Failure" + it.message)
            }
    }

    fun createCustomExercise(userId: String, exercise: WsExercise) {
        db.collection(USER_EXERCISES_COLLECTION)
            .document(userId)
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

    fun getAllCustomExercises(userId: String): Flow<List<WsExercise>> = callbackFlow {
        db.collection(USER_EXERCISES_COLLECTION)
            .document(userId)
            .collection(CUSTOM_EXERCISE_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                trySend(result.documents.map {
                    it.toObject()!!
                })
            }
        awaitClose { close() }
    }
}