package com.teamsparta.courseresistration.domain.lecture.model

import com.teamsparta.courseresistration.domain.course.model.Course
import com.teamsparta.courseresistration.domain.lecture.dto.LectureResponse
import jakarta.persistence.*

@Entity
@Table(name="lecture")
class Lecture (

    @Column(name="title", nullable=false)
    var title: String,

    @Column(name="video_url", nullable=false)
    var videoUrl : String,

    @ManyToOne
    @JoinColumn( name="course_id", nullable=false)
    var course : Course

        ){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null
}

fun Lecture.toResponse(): LectureResponse {
    return LectureResponse(
        id = id!!,
        title = title,
        videoUrl = videoUrl,
    )
}