package com.teamsparta.courseresistration.domain.course.service

import com.teamsparta.courseresistration.domain.course.dto.CourseResponse
import com.teamsparta.courseresistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseresistration.domain.course.dto.UpdateCourseRequest
import com.teamsparta.courseresistration.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseresistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseresistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.courseresistration.domain.lecture.dto.AddLectureRequest
import com.teamsparta.courseresistration.domain.lecture.dto.LectureResponse
import com.teamsparta.courseresistration.domain.lecture.dto.UpdateLectureRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface CourseService {
    /**"course" Aggregation
     * : Lecture, courseApplicaiton 관련 command도 CourseService를 통해 처리 (For 단순화 )
     * ( 선택의 문제! )
     *
     *=> 각각의 service 따로 구현하고 courseService에 주입해도 됨.
     */

    //courseService
    fun getAllCourseList() : List<CourseResponse>

    fun getCourseById(courseId : Long): CourseResponse

    fun createCourse(request : CreateCourseRequest): CourseResponse

    fun updateCourse( courseId:Long, request: UpdateCourseRequest) : CourseResponse

    fun deleteCourse( courseId: Long)

    //lectureService
    fun getLectureList(courseId: Long): List<LectureResponse>

    fun getLecture(courseId: Long, lectureId: Long): LectureResponse

    fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse

    fun updateLecture(
        courseId: Long,
        lectureId: Long,
        request: UpdateLectureRequest
    ): LectureResponse

    fun removeLecture(courseId: Long, lectureId: Long)

    //CourseApplicationService
    fun applyCourse( courseId: Long, applyCourseRequest: ApplyCourseRequest) : CourseApplicationResponse
    fun getApplicationList( courseId: Long) : List<CourseApplicationResponse>
    fun getApplication (courseId: Long, applicationId : Long) : CourseApplicationResponse
    fun updateApplicationStatus (courseId: Long, applicationId : Long,updateApplicationStatusRequest: UpdateApplicationStatusRequest ) : CourseApplicationResponse

}