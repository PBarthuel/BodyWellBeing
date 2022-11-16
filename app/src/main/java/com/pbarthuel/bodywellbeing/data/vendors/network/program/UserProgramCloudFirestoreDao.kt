package com.pbarthuel.bodywellbeing.data.vendors.network.program

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.data.model.article.WsArticle
import com.pbarthuel.bodywellbeing.data.model.program.WsProgramDetail
import javax.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserProgramCloudFirestoreDao @Inject constructor() {

    companion object {
        const val JOINED_COLLECTION = "joined"
        const val USER_PROGRAM_COLLECTION = "userProgram"
    }

    private val db = Firebase.firestore

    fun joinProgram(userId: String, joinedProgram: WsProgramDetail) {
        db.collection(USER_PROGRAM_COLLECTION)
            .document(userId)
            .collection(JOINED_COLLECTION)
            .document(joinedProgram.id)
            .set(joinedProgram)
            .addOnSuccessListener {
                Log.d("UserProgramCloudFirestoreDao", "joinProgram: success")
            }
            .addOnFailureListener {
                Log.d("UserProgramCloudFirestoreDao", "joinProgram: Failure" + it.message)
            }
    }

    fun getJoinedProgram(userId: String): Flow<WsProgramDetail?> = callbackFlow {
        db.collection(USER_PROGRAM_COLLECTION)
            .document(userId)
            .collection(JOINED_COLLECTION)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.d("ProgramCloudFirestoreDao", "Listen failed ")
                }

                if (value != null && !value.isEmpty) {
                    trySend(value.documents.first().toObject<WsProgramDetail>())
                } else {
                    Log.d("ProgramCloudFirestoreDao", "Fail to retrieving data ")
                }
            }
        awaitClose { close() }
    }

    fun leaveProgram(userId: String, programId: String) {
        db.collection(USER_PROGRAM_COLLECTION)
            .document(userId)
            .collection(JOINED_COLLECTION)
            .document(programId)
            .delete()
            .addOnSuccessListener {
                Log.d("UserProgramCloudFirestoreDao", "leaveProgram: success")
            }
            .addOnFailureListener {
                Log.d("UserProgramCloudFirestoreDao", "leaveProgram: Failure" + it.message)
            }
    }
}