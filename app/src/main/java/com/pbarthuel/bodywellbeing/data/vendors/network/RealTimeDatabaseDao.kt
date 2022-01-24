package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.domain.repositories.local.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class RealTimeDatabaseDao @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository
) {

    private val db = FirebaseDatabase.getInstance("https://bodywellbeing-default-rtdb.europe-west1.firebasedatabase.app/").getReference(USER_DATABASE_REF)

    suspend fun getUser(userId: String, email: String): User? = callbackFlow {
        db.child(userId).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    kotlin.runCatching { it.getValue(User::class.java) }
                        .onSuccess { user -> user?.let { userNotNull ->
                            runBlocking(dispatcher.io) {
                                preferenceDataStoreRepository.saveToDataStore(
                                    userId = userNotNull.uid,
                                    email = userNotNull.email,
                                    isAlreadyCreated = userNotNull.alreadyCreated
                                )
                                trySend(userNotNull)
                            }
                        } }.onFailure { throw Exception("User not deserialized") }
                } else {
                    kotlin.runCatching {
                        runBlocking(dispatcher.io) {
                            db.child(userId).setValue(User(uid = userId, email = email, alreadyCreated = false))
                            preferenceDataStoreRepository.saveToDataStore(userId = userId, email = email, isAlreadyCreated = false)
                        }
                    }.onSuccess { trySend(null) }
                        .onFailure { throw Exception("createUserForFirstTime Fail") }
                }
            }.addOnFailureListener { Log.e("firebase", "Error getting data", it) }
        awaitClose { cancel() }
    }.first()

    companion object {
        const val USER_DATABASE_REF = "users"
    }
}