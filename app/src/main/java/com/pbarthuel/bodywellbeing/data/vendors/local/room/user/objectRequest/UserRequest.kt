package com.pbarthuel.bodywellbeing.data.vendors.local.room.user.objectRequest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pbarthuel.bodywellbeing.app.models.User
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class UserRequest(
    @PrimaryKey
    val uid: String = "",
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "first_name")
    val firstName: String = "",
    @ColumnInfo(name = "last_name")
    val lastName: String = "",
    @ColumnInfo(name = "age")
    val age: Int = 0,
    @ColumnInfo(name = "height")
    val height: Int = 0,
    @ColumnInfo(name = "weight")
    val weight: Int = 0,
    @ColumnInfo(name = "already_created")
    val alreadyCreated: Boolean = true
) {
    fun toUser(): User {
        return User(
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
