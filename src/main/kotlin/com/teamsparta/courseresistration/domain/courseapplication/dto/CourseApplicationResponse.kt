package com.teamsparta.courseresistration.domain.courseapplication.dto

import com.teamsparta.courseresistration.domain.course.dto.CourseResponse
import com.teamsparta.courseresistration.domain.user.dto.UserResponse

data class CourseApplicationResponse (
    val id: Long,
    val course : CourseResponse,
    val user: UserResponse,
    val status: String,
        )

//status만 줘도 되겠지만, if 굳이 하위url을 조회하지않게 course정보/user정보도 주고싶다면  ~