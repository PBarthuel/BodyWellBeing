package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.data.model.program.WsProgram
import javax.inject.Inject
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ProgramCloudFirestoreDao @Inject constructor() {

    private val db = Firebase.firestore.collection("program")

    fun createProgram(program: WsProgram) {
        db.document(program.id)
            .set(program)
            .addOnSuccessListener {
                Log.d("ProgramCloudFirestoreDao", "createProgram: success")
            }
            .addOnFailureListener {
                Log.d("ProgramCloudFirestoreDao", "createProgram: Failure" + it.message)
            }
    }

    fun getAllPrograms(): Flow<List<WsProgram>> = callbackFlow {
        db.addSnapshotListener { value, error ->
            if (error != null) {
                Log.d("ProgramCloudFirestoreDao", "Listen failed ")
            }

            if (value != null && !value.isEmpty) {
                trySend(value.documents.map {
                    it.toObject<WsProgram>()!!
                })
            } else {
                Log.d("ProgramCloudFirestoreDao", "Fail to retrieving data ")
            }
        }
        awaitClose { cancel() }
    }
}