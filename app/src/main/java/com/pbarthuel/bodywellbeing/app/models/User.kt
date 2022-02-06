package com.pbarthuel.bodywellbeing.app.models

import com.google.firebase.database.IgnoreExtraProperties
import com.pbarthuel.bodywellbeing.data.vendors.local.room.user.entities.UserEntity
import kotlinx.serialization.Serializable

@IgnoreExtraProperties
@Serializable
data class User(
    val uid: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val age: Int = 0,
    val height: Int = 0,
    val weight: Double = 0.0,
    val alreadyCreated: Boolean = true
) {
    fun toUserEntity(): UserEntity {
        return UserEntity(
            uid = uid,
            email = email,
            firstName = firstName,
            lastName = lastName,
            age = age,
            height = height,
            weight = weight,
            alreadyCreated = alreadyCreated
        )
    }
}