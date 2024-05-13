package com.teamsparta.courseresistration.domain.courseapplication.repository

import com.teamsparta.courseresistration.domain.course.model.Course
import com.teamsparta.courseresistration.domain.courseapplication.model.CourseApplication
import org.springframework.data.jpa.repository.JpaRepository

interface CourseApplicationRepository : JpaRepository<CourseApplication, Long> {
}