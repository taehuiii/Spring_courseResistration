package com.teamsparta.courseresistration.domain.course.service
import com.teamsparta.courseresistration.domain.exception.dto.ErrorResponse
import com.teamsparta.courseresistration.domain.course.dto.CourseResponse
import com.teamsparta.courseresistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseresistration.domain.course.dto.UpdateCourseRequest
import com.teamsparta.courseresistration.domain.course.model.Course
import com.teamsparta.courseresistration.domain.course.model.CourseStatus
import com.teamsparta.courseresistration.domain.course.model.toResponse
import com.teamsparta.courseresistration.domain.course.repository.CourseRepository
import com.teamsparta.courseresistration.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseresistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseresistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.courseresistration.domain.courseapplication.model.CourseApplication
import com.teamsparta.courseresistration.domain.courseapplication.model.CourseApplicationStatus
import com.teamsparta.courseresistration.domain.courseapplication.model.toResponse
import com.teamsparta.courseresistration.domain.courseapplication.repository.CourseApplicationRepository
import com.teamsparta.courseresistration.domain.exception.ModelNotFoundException
import com.teamsparta.courseresistration.domain.lecture.dto.AddLectureRequest
import com.teamsparta.courseresistration.domain.lecture.dto.LectureResponse
import com.teamsparta.courseresistration.domain.lecture.dto.UpdateLectureRequest
import com.teamsparta.courseresistration.domain.lecture.model.Lecture
import com.teamsparta.courseresistration.domain.lecture.model.toResponse
import com.teamsparta.courseresistration.domain.lecture.repository.LectureRepository
import com.teamsparta.courseresistration.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

import org.springframework.web.bind.annotation.ExceptionHandler

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val lectureRepository: LectureRepository,
    private val courseApplicationRepository: CourseApplicationRepository,
    private val userRepository: UserRepository,
) : CourseService {
    override fun getAllCourseList(): List<CourseResponse> {
        //DB에서 모든 course(Entity)가져와서, CourseResponse(DTO)로 변환 후 반환
        return courseRepository.findAll().map { it.toResponse() }
    }

    override fun getCourseById(courseId: Long): CourseResponse {
        //만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //DB에서 id에 해당하는 Course Entity가져와서 CourseResponse(DTO)로 변환 후 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.toResponse()
    }

    @Transactional
    override fun createCourse(request: CreateCourseRequest): CourseResponse {
        //request(DTO)를 Entity로 변환 후 DB에 저장
        return courseRepository.save(
            Course(
                title = request.title,
                description = request.description,
                status = CourseStatus.OPEN,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        //만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //DB에서 해당하는 courseEntity가져와서 수정 및 저장 ->결과 CourseResponse(DTO)로 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val (title, description) = request

        course.title = title
        course.description = description

        return courseRepository.save(course).toResponse()
    }

    @Transactional
    override fun deleteCourse(courseId: Long) {
       //만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //DB에서 courseID에 해당하는 Course를 삭제, 연관된 CourseApplication과 Lecture도 모두 삭제
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        courseRepository.delete(course)
    }

    override fun getLectureList(courseId: Long): List<LectureResponse> {
        // 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //DB에서 courseID에 해당하는 course의 모든 LEcture Entity가져와서 LectureResponse(DTO)로 변환 후 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.lectures.map { it.toResponse() }
    }

    override fun getLecture(courseId: Long, lectureId: Long): LectureResponse {
        // 만약 courseId,lectureId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //DB에서 courseID에 해당하는 Course의, lectureid에 해당하는 lecture Entity를 LectureResponse(DTO)로 변환 후 반환
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId)
            ?: throw ModelNotFoundException("Lecture", lectureId)

        return lecture.toResponse()
    }

    @Transactional
    override fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse {
        // 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //DB에서 courseID에 해당하는 Course Entity가져와서, requestDTO -> Lecture Entity 추가 후 DB에 저장, 결과는 LectureResponse(DTO)로 변환 후 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        val lecture = Lecture(
            title = request.title,
            videoUrl = request.videoUrl,
            course = course
        )
        // Course에 Lecture 추가
        course.addLecture(lecture)
        // Lecture에 영속성을 전파
        courseRepository.save(course)
        return lecture.toResponse()
    }

    @Transactional
    override fun updateLecture(courseId: Long, lectureId: Long, request: UpdateLectureRequest): LectureResponse {
        // 만약 courseId,lectureId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //DB에서 courseId, lectureId에 해당하는 Lecture Entity를 가져와서 request대로 업데이트 후 DB에 저장, 결과를을 LectureResponse로 변환 후 반환
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId)
            ?: throw ModelNotFoundException("Lecture", lectureId)

        val (title, videoUrl) = request
        lecture.title = title
        lecture.videoUrl = videoUrl

        return lectureRepository.save(lecture).toResponse()
    }

    @Transactional
    override fun removeLecture(courseId: Long, lectureId: Long) {
        //만약 courseId,lectureId에 해당하는 Course가 없다면 throw ModelNotFoundException
       // DB에서 courseId, lectureId에 해당하는 Lecture를 가져와서 삭제
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val lecture = lectureRepository.findByIdOrNull(lectureId)
            ?: throw ModelNotFoundException("Lecture", lectureId)

        course.removeLecture(lecture)

        // Lecture에 영속성을 전파
        courseRepository.save(course)
    }

    @Transactional
    override fun applyCourse(courseId: Long, applyCourseRequest: ApplyCourseRequest): CourseApplicationResponse {


        // 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // 만약 course가 이미 마감됐다면, throw IllegalStateException
        // 이미 신청했다면, throw IllegalStateException

        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val user = userRepository.findByIdOrNull(request.userId)
            ?: throw ModelNotFoundException("User", request.userId)

        // Course 마감여부 체크
        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        // CourseApplication 중복 체크
        if (courseApplicationRepository.existsByCourseIdAndUserId(courseId, request.userId)) {
            throw IllegalStateException("Already applied. courseId: $courseId, userId: ${request.userId}")
        }

        val courseApplication = CourseApplication(
            course = course,
            user = user,
        )
        course.addCourseApplication(courseApplication)
        // CourseApplication에 영속성을 전파
        courseRepository.save(course)

        return courseApplication.toResponse()
    }

    override fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse> {
        // 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // DB에서 courseId에 해당하는 Course(Entity)를 가져오고, 하위 courseApplication(Entity)들을 CourseApplicationResponse(DTO)로 변환 후 반환

        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.courseApplications.map { it.toResponse() }
    }

    override fun getCourseApplication(courseId: Long, applicationId: Long): CourseApplicationResponse {
        // 만약 courseId, applicationId에 해당하는 CourseApplication이 없다면 throw ModelNotFoundException
        // DB에서 courseId, applicationId에 해당하는 CourseApplication(Entity)을 가져와서 CourseApplicationResponse(DTO)로 변환 후 반환
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        return application.toResponse()
    }

    @Transactional
    override fun updateApplicationStatus(
        courseId: Long,
        applicationId: Long,
        updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {

        // 만약 courseId, applicationId에 해당하는 CourseApplication이 없다면 throw ModelNotFoundException
        // 만약 status가 이미 변경된 상태면 throw IllegalStateException
        // Course의 status가 CLOSED상태 일시 throw IllegalStateException

        // 승인을 하는 케이스일 경우, course의 numApplicants와 maxApplicants가 동일하면, course의 상태를 CLOSED로 변경
        // DB에서 courseApplication을 가져오고, status를 request로 업데이트 후 DB에 저장, 결과를 CourseApplicationResponse로 변환 후 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        // 이미 승인 혹은 거절된 신청건인지 체크
        if (application.isProceeded()) {
            throw IllegalStateException("Application is already proceeded. applicationId: $applicationId")
        }

        // Course 마감여부 체크
        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        // 승인 / 거절 따른 처리
        when (updateApplicationStatusRequest.status) {
            // 승인 일때
            CourseApplicationStatus.ACCEPTED.name -> {
                // 승인 처리
                application.accept()
                // Course의 신청 인원 늘려줌
                course.addApplicant()
                // 만약 신청 인원이 꽉 찬다면 마감 처리
                if (course.isFull()) {
                    course.close()
                }
                courseRepository.save(course)
            }

            // 거절 일때
            CourseApplicationStatus.REJECTED.name -> {
                // 거절 처리
                application.reject()
            }
            // 승인 거절이 아닌 다른 인자가 들어올 경우 에러 처리
            else -> {
                throw IllegalArgumentException("Invalid status: ${updateApplicationStatusRequest.status}")
            }
        }

        return courseApplicationRepository.save(application).toResponse()
    }

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException( e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.message))
    }
}

