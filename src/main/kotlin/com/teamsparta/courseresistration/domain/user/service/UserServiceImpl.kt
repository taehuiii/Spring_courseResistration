package com.teamsparta.courseresistration.domain.user.service

import com.teamsparta.courseresistration.domain.user.dto.SignUpRequest
import com.teamsparta.courseresistration.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.courseresistration.domain.user.dto.UserResponse

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl : UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        // TODO: Email이 중복되는지 확인, 중복된다면 throw IllegalStateException

        //Todo : request(DTO)를 User(Entity)로 변환 후 DB에 저장, UserResponse(DTO)로 반환
        //Todo : 비밀번호는 저장 시 암호화

        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateUserProfile(userId: Long, updateUserProfileRequest: UpdateUserProfileRequest): UserResponse {
        // TODO: 만약 userId에 해당하는 User가 없다면 throw ModelNotFoundException
        //Todo: DB에서 userId에 해당하는 User Entity찾아서, updateUserProfileRequest로 업데이트 후 DB에 저장, 결과 UserResponse로 변환후반환

        TODO("Not yet implemented")
    }
}