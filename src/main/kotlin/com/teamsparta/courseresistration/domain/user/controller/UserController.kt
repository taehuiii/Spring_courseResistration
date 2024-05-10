package com.teamsparta.courseresistration.domain.user.controller;

import com.teamsparta.courseresistration.domain.user.dto.SignUpRequest;
import com.teamsparta.courseresistration.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.courseresistration.domain.user.dto.UserResponse;
import com.teamsparta.courseresistration.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RequestMapping() 안하는 이유 : users로 공통적으로 묶이는 uri가 없어서
//로그인,로그아웃은 인증필요해서 나중에 구현

@RestController
public class UserController(
    private val userService: UserService){

    @PostMapping("/signup")
    fun signUp( @RequestBody signUpRequest :SignUpRequest):ResponseEntity<UserResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signUpRequest))
    }

    @PutMapping("/users/{userID}/profile")
    fun updateUserProfile(@PathVariable userId : Long, @RequestBody updateUserProfileRequest: UpdateUserProfileRequest ): ResponseEntity<UserResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateUserProfile(userId, updateUserProfileRequest))
    }


}
