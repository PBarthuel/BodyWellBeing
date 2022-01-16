package com.pbarthuel.bodywellbeing.data.vendors.network

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.pbarthuel.bodywellbeing.app.models.User
import javax.inject.Inject

class RealTimeDatabaseDao @Inject constructor() {

    private val db = FirebaseDatabase.getInstance().reference

    fun createUser(user: User) {
        db.child(USER_DATABASE_REF).child(user.uid).setValue(user)
    }

    fun getUser(userId: String): User {
        val user: User = User(uid = "", email = "", displayName = "")
        db.child(USER_DATABASE_REF).child(userId).get()
            .addOnSuccessListener {
                // TODO ici recupérer le user et l'enregistrer en local dans les prefs
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        return user
    }

    companion object {
        const val USER_DATABASE_REF = "users"
    }
}