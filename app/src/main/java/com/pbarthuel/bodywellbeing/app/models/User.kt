package com.pbarthuel.bodywellbeing.app.models

data class User(
    val userId: String = "",
    val email: String,
    val displayName: String?
)