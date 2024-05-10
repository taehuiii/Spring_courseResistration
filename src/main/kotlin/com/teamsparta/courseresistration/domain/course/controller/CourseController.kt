package com.teamsparta.courseresistration.domain.course.controller

import com.teamsparta.courseresistration.domain.course.dto.CourseResponse
import com.teamsparta.courseresistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseresistration.domain.course.dto.UpdateCourseRequest
import com.teamsparta.courseresistration.domain.course.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/course") //Handler Mapping에게 어떤 url을 담당하는지 알려줌 ( base url)
@RestController //Spring Bean으로 등록하도록 (REST : view가 아닌 data만을 응답하기 때문 )
class CourseController(
    private val courseService : CourseService //생성자 주입 (서비스의 인터페이스 ok)
) {

    //course 관련한 각각의 command에 대한 API 작성 -> Handler Method이용해서 verb 표현
    @GetMapping()//course 목록 조회
    fun getCourseList(): ResponseEntity<List<CourseResponse>> { //ResponseEntity라는게 있나보지
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getAllCourseList())
    }

    @GetMapping("/{courseId}") //course 목록 단건 조회
    fun getCourse(@PathVariable courseId: Long ) : ResponseEntity<CourseResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseById(courseId))
    }

    @PostMapping() //course 생성 -> RequestDTO를 Argument로 받아야함.
    fun createCourse(@RequestBody createCourseRequest: CreateCourseRequest) : ResponseEntity<CourseResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(courseService.createCourse(createCourseRequest))
    }

    @PutMapping("/{courseId}")  //Course 수정
    fun updateCourse(
        @PathVariable courseId: Long,
        @RequestBody updateCourseRequest: UpdateCourseRequest
    ):ResponseEntity<CourseResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.updateCourse(courseId,updateCourseRequest))
    }

    @DeleteMapping("/{courseId}") //Course 삭제
    fun deleteCourse(@PathVariable courseId: Long) : ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}





