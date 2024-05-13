package com.teamsparta.courseresistration.domain.course.model

import com.teamsparta.courseresistration.domain.course.dto.CourseResponse
import com.teamsparta.courseresistration.domain.courseapplication.model.CourseApplication
import com.teamsparta.courseresistration.domain.lecture.model.Lecture
import jakarta.persistence.*


@Entity
@Table(name="Course")
class Course(

    @Column(name="title", nullable=false)
    var title:String, //콤마로 연결!!

    @Column(name="description")
    var description: String?=null, //필수값 아님.

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    var status : CourseStatus = CourseStatus.OPEN,

    @Column(name="max_applicants", nullable=false)
    val maxApplicants : Int =30, //val 선언

    @Column(name="num_applicants", nullable=false)
    var numApplicants : Int =0,

    @OneToMany( mappedBy = "course", cascade = [CascadeType.ALL], orphanRemoval = true)
    var lectures : MutableList<Lecture> = mutableListOf(),

    @OneToMany( mappedBy="course", cascade=[CascadeType.ALL], orphanRemoval = true)
    var courseApplications : MutableList<CourseApplication> = mutableListOf()

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? =null


    fun isFull(): Boolean {
        return numApplicants >= maxApplicants
    }

    fun isClosed(): Boolean {
        return status == CourseStatus.CLOSED
    }

    fun close() {
        status = CourseStatus.CLOSED
    }

    fun addApplicant() {
        numApplicants += 1
    }

    fun addLecture(lecture: Lecture) {
        lectures.add(lecture)
    }

    fun removeLecture(lecture: Lecture) {
        lectures.remove(lecture)
    }

    fun addCourseApplication(courseApplication: CourseApplication) {
        courseApplications.add(courseApplication)
    }


}

fun Course.toResponse(): CourseResponse {
    return CourseResponse(
        id = id!!,
        title = title,
        description = description,
        status = status.name,
        maxApplicants = maxApplicants,
        numApplicants = numApplicants
    )
}
