package com.pbarthuel.bodywellbeing.app.models

data class User(
    val uid: String = "",
    val email: String,
    val displayName: String?
)