package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.UserRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class RealTimeDatabaseDao @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val userRepository: UserRepository
) {

    private val db = FirebaseDatabase
        .getInstance("https://bodywellbeing-default-rtdb.europe-west1.firebasedatabase.app/")
        .getReference(USER_DATABASE_REF)

    suspend fun checkIfUserAlreadyExist(userId: String, email: String) {
        db.child(userId).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    kotlin.runCatching { it.getValue(User::class.java) }
                        .onSuccess { user ->
                            user?.let { userNotNull ->
                                runBlocking(dispatcher.io) {
                                    /**
                                     * User already exist so you save the user in local Room UserDb
                                     * And you save his UserId and his creation state in prefDatastore
                                     */
                                    preferenceDataStoreRepository.saveToDataStore(
                                        userId = userId,
                                        isAlreadyCreated = userNotNull.alreadyCreated
                                    )
                                    userRepository.createUser(userNotNull)
                                }
                            }
                        }.onFailure { throw Exception("User not deserialized") }
                } else {
                    kotlin.runCatching {
                        runBlocking(dispatcher.io) {
                            /**
                             * User doesn't already exist so you create a user on RealTimeDatabase
                             * And you save his UserId and his creation state in prefDatastore
                             */
                            db.child(userId).setValue(User(uid = userId, email = email, alreadyCreated = false))
                            preferenceDataStoreRepository.saveToDataStore(userId = userId, isAlreadyCreated = false)
                        }
                    }.onFailure { throw Exception("createUserForFirstTime Fail") }
                }
            }.addOnFailureListener { Log.e("firebase", "Error getting data", it) }
    }

    suspend fun updateUserFromAccountCreation(user: User) {
        kotlin.runCatching {
            db.child(user.uid).setValue(user)
        }.onSuccess {
            preferenceDataStoreRepository.saveToDataStore(
                userId = user.uid,
                isAlreadyCreated = true
            )
        }
    }

    companion object {
        const val USER_DATABASE_REF = "users"
    }
}