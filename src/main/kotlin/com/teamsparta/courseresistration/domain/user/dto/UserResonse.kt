package com.teamsparta.courseresistration.domain.user.dto

data class UserResponse (
    val id : Long,
    val email : String,
    val nickname : String,
    val role: String,
        )
//비밀번호는 민감한 정보니까 응답으로 보내주지 않음.