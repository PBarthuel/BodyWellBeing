package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.data.model.article.WsArticle
import javax.inject.Inject
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ArticleCloudFirestoreDao @Inject constructor() {

    private val db = Firebase.firestore.collection("article")

    fun createArticle(article: WsArticle) {
        db.document(article.id)
            .set(article)
            .addOnSuccessListener {
                Log.d("ExerciseCloudFirestoreDao", "createArticle: success")
            }
            .addOnFailureListener {
                Log.d("ExerciseCloudFirestoreDao", "createArticle: Failure" + it.message)
            }
    }

    fun getAllArticles(): Flow<List<WsArticle>> = callbackFlow {
        db.addSnapshotListener { value, error ->
            if (error != null) {
                Log.d("ArticleCloudFirestoreDao", "Listen failed ")
            }

            if (value != null && !value.isEmpty) {
                trySend(value.documents.map {
                    it.toObject<WsArticle>()!!
                })
            } else {
                Log.d("ArticleCloudFirestoreDao", "Fail to retrieving data ")
            }
        }
        awaitClose { cancel() }
    }
}