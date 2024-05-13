package com.teamsparta.courseresistration.domain.course.repository

import com.teamsparta.courseresistration.domain.course.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository:JpaRepository<Course, Long> {
}