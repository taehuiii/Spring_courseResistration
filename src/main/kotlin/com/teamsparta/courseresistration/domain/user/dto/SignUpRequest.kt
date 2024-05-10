package com.teamsparta.courseresistration.domain.user.dto

data class SignUpRequest (

    val password : String,
    val email : String,
    val nickname : String,
    val role: String,
        )