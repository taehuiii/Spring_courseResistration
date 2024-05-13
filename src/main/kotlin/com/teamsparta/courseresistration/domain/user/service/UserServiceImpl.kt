package com.teamsparta.courseresistration.domain.user.service

import com.teamsparta.courseresistration.domain.exception.ModelNotFoundException
import com.teamsparta.courseresistration.domain.user.dto.SignUpRequest
import com.teamsparta.courseresistration.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.courseresistration.domain.user.dto.UserResponse
import com.teamsparta.courseresistration.domain.user.model.Profile
import com.teamsparta.courseresistration.domain.user.model.User
import com.teamsparta.courseresistration.domain.user.model.UserRole
import com.teamsparta.courseresistration.domain.user.model.toResponse
import com.teamsparta.courseresistration.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        // Email이 중복되는지 확인, 중복된다면 throw IllegalStateException
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw IllegalStateException("Email is already in use")
        }
        //request(DTO)를 User(Entity)로 변환 후 DB에 저장, UserResponse(DTO)로 반환
        //비밀번호는 저장 시 암호화
        return userRepository.save(
            User(
                email = signUpRequest.email,
                // TODO: 비밀번호 암호화
                password = signUpRequest.password,
                profile = Profile(
                    nickname = signUpRequest.nickname
                ),
                role = when (signUpRequest.role) {
                    UserRole.STUDENT.name -> UserRole.STUDENT
                    UserRole.TUTOR.name -> UserRole.TUTOR
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        ).toResponse()


    }

    @Transactional
    override fun updateUserProfile(userId: Long, updateUserProfileRequest: UpdateUserProfileRequest): UserResponse {
        //만약 userId에 해당하는 User가 없다면 throw ModelNotFoundException
        //DB에서 userId에 해당하는 User Entity찾아서, updateUserProfileRequest로 업데이트 후 DB에 저장, 결과 UserResponse로 변환후반환

        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = updateUserProfileRequest.nickname
        )

        return userRepository.save(user).toResponse()
    }
}