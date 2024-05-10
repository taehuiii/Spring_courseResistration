package com.teamsparta.courseresistration.domain.course.service
import com.teamsparta.courseresistration.domain.exception.dto.ErrorResponse
import com.teamsparta.courseresistration.domain.course.dto.CourseResponse
import com.teamsparta.courseresistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseresistration.domain.course.dto.UpdateCourseRequest
import com.teamsparta.courseresistration.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseresistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseresistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.courseresistration.domain.exception.ModelNotFoundException
import com.teamsparta.courseresistration.domain.lecture.dto.AddLectureRequest
import com.teamsparta.courseresistration.domain.lecture.dto.LectureResponse
import com.teamsparta.courseresistration.domain.lecture.dto.UpdateLectureRequest
import org.springframework.transaction.annotation.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

import org.springframework.web.bind.annotation.ExceptionHandler

@Service
class CourseServiceImpl : CourseService {
    override fun getAllCourseList(): List<CourseResponse> {
        //Todo : DB에서 모든 course(Entity)가져와서, CourseResponse(DTO)로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun getCourseById(courseId: Long): CourseResponse {
        //Todo : 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //Todo : DB에서 id에 해당하는 Course Entity가져와서 CourseResponse(DTO)로 변환 후 반환
        TODO("Not yet implemented")
    }

    @Transactional
    override fun createCourse(request: CreateCourseRequest): CourseResponse {
        //Todo : request(DTO)를 Entity로 변환 후 DB에 저장
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        //Todo: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //Todo : DB에서 해당하는 courseEntity가져와서 수정 및 저장 ->결과 CourseResponse(DTO)로 반환
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteCourse(courseId: Long) {
       //Todo 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //Todo : DB에서 courseID에 해당하는 Course를 삭제, 연관된 CourseApplication과 Lecture도 모두 삭제
        TODO("Not yet implemented")
    }

    override fun getLectureList(courseId: Long): List<LectureResponse> {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //Todo : DB에서 courseID에 해당하는 course의 모든 LEcture Entity가져와서 LectureResponse(DTO)로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun getLecture(courseId: Long, lectureId: Long): LectureResponse {
        // TODO: 만약 courseId,lectureId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //Todo : DB에서 courseID에 해당하는 Course의, lectureid에 해당하는 lecture Entity를 LectureResponse(DTO)로 변환 후 반환
        TODO("Not yet implemented")
    }

    @Transactional
    override fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //Todo : DB에서 courseID에 해당하는 Course Entity가져와서, requestDTO -> Lecture Entity 추가 후 DB에 저장, 결과는 LectureResponse(DTO)로 변환 후 반환
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateLecture(courseId: Long, lectureId: Long, request: UpdateLectureRequest): LectureResponse {
        // TODO: 만약 courseId,lectureId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //TODO: DB에서 courseId, lectureId에 해당하는 Lecture Entity를 가져와서 request대로 업데이트 후 DB에 저장, 결과를을 LectureResponse로 변환 후 반환
        TODO("Not yet implemented")
    }

    @Transactional
    override fun removeLecture(courseId: Long, lectureId: Long) {
        // TODO: 만약 courseId,lectureId에 해당하는 Course가 없다면 throw ModelNotFoundException
       // TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져와서 삭제

        TODO("Not yet implemented")
    }

    @Transactional
    override fun applyCourse(courseId: Long, applyCourseRequest: ApplyCourseRequest): CourseApplicationResponse {
        //왜 예외처리만 ?

        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: 만약 course가 이미 마감됐다면, throw IllegalStateException
        // TODO: 이미 신청했다면, throw IllegalStateException

        TODO("Not yet implemented")
    }

    override fun getApplicationList(courseId: Long): List<CourseApplicationResponse> {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course(Entity)를 가져오고, 하위 courseApplication(Entity)들을 CourseApplicationResponse(DTO)로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun getApplication(courseId: Long, applicationId: Long): CourseApplicationResponse {
        // TODO: 만약 courseId, applicationId에 해당하는 CourseApplication이 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, applicationId에 해당하는 CourseApplication(Entity)을 가져와서 CourseApplicationResponse(DTO)로 변환 후 반환

        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateApplicationStatus(
        courseId: Long,
        applicationId: Long,
        updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {

        // TODO: 만약 courseId, applicationId에 해당하는 CourseApplication이 없다면 throw ModelNotFoundException
        // TODO: 만약 status가 이미 변경된 상태면 throw IllegalStateException
        // TODO: Course의 status가 CLOSED상태 일시 throw IllegalStateException

        // TODO: 승인을 하는 케이스일 경우, course의 numApplicants와 maxApplicants가 동일하면, course의 상태를 CLOSED로 변경
        // TODO: DB에서 courseApplication을 가져오고, status를 request로 업데이트 후 DB에 저장, 결과를 CourseApplicationResponse로 변환 후 반환
        TODO("Not yet implemented")
    }

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException( e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.message))
    }
}