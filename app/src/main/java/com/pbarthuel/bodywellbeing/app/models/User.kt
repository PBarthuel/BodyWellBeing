package com.pbarthuel.bodywellbeing.app.models

import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@IgnoreExtraProperties
@Serializable
data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String? = null,
    val alreadyCreated: Boolean = true
)