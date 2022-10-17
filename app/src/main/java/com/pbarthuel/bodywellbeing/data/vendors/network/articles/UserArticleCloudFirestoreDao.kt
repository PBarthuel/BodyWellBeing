package com.pbarthuel.bodywellbeing.data.vendors.network.articles

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.data.model.article.WsArticle
import javax.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserArticleCloudFirestoreDao @Inject constructor() {

    companion object {
        const val FAVORITE_COLLECTION = "favorite"
        const val USER_ARTICLES_COLLECTION = "userArticle"
    }

    private val db = Firebase.firestore

    fun addArticleToFavorite(userId: String, article: WsArticle) {
        db.collection(USER_ARTICLES_COLLECTION)
            .document(userId)
            .collection(FAVORITE_COLLECTION)
            .document(article.id)
            .set(article)
            .addOnSuccessListener {
                Log.d("UserArticleCloudFirestoreDao", "addArticleToFavorite: success")
            }
            .addOnFailureListener {
                Log.d("UserArticleCloudFirestoreDao", "addArticleToFavorite: Failure" + it.message)
            }
    }

    fun getAllFavoriteArticles(userId: String): Flow<List<WsArticle>> = callbackFlow {
        db.collection(USER_ARTICLES_COLLECTION)
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

    fun deleteArticleFromFavorite(userId: String, articleId: String) {
        db.collection(USER_ARTICLES_COLLECTION)
            .document(userId)
            .collection(FAVORITE_COLLECTION)
            .document(articleId)
            .delete()
            .addOnSuccessListener {
                Log.d("UserArticleCloudFirestoreDao", "deleteArticleFromFavorite: success")
            }
            .addOnFailureListener {
                Log.d("UserArticleCloudFirestoreDao", "deleteExerciseFromFavorite: Failure" + it.message)
            }
    }
}