package com.teamsparta.courseresistration.domain.user.service

import com.teamsparta.courseresistration.domain.user.dto.SignUpRequest
import com.teamsparta.courseresistration.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.courseresistration.domain.user.dto.UserResponse

interface UserService {
    fun signUp (signUpRequest: SignUpRequest) : UserResponse

    fun updateUserProfile(userId : Long, updateUserProfileRequest: UpdateUserProfileRequest): UserResponse
}