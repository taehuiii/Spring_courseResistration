package com.teamsparta.courseresistration.domain.course.dto

data class CourseResponse (

    val id : Long,
    val email : String,
    val description : String?,
    val status: String,
    val maxApplicants: Int,
    val numApplicants: Int

        )