package com.teamsparta.courseresistration.domain.courseapplication.model

import com.teamsparta.courseresistration.domain.course.model.Course
import com.teamsparta.courseresistration.domain.course.model.toResponse
import com.teamsparta.courseresistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseresistration.domain.user.model.User
import com.teamsparta.courseresistration.domain.user.model.toResponse
import jakarta.persistence.*

@Entity
@Table(name="CourseApplication")
class CourseApplication(

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    var status : CourseApplicationStatus =CourseApplicationStatus.PENDING,

    @ManyToOne
    @JoinColumn( name="course_id", nullable = false)
    val course : Course,

    @ManyToOne
    @JoinColumn( name="user_id", nullable = false)
    val user : User,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null

    fun isProceeded(): Boolean {
        return status != CourseApplicationStatus.PENDING
    }

    fun accept() {
        status = CourseApplicationStatus.ACCEPTED
    }

    fun reject() {
        status = CourseApplicationStatus.REJECTED
    }
}
fun CourseApplication.toResponse(): CourseApplicationResponse {
    return CourseApplicationResponse(
        id = id!!,
        course = course.toResponse(),
        user = user.toResponse(),
        status = status.name
    )
}