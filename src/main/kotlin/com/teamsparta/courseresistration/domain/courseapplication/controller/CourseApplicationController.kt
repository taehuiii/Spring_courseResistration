package com.teamsparta.courseresistration.domain.courseapplication.controller

import com.teamsparta.courseresistration.domain.course.service.CourseService
import com.teamsparta.courseresistration.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseresistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseresistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/courses/{courseId}/applications")
@RestController
class CourseApplicationController(
    private val courseService: CourseService
) {

    @PostMapping
    fun applyCourse(@PathVariable courseId : Long, @RequestBody applyCourseRequest: ApplyCourseRequest) : ResponseEntity<CourseApplicationResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(courseService.applyCourse(courseId, applyCourseRequest))
    }

    @GetMapping()
    fun getApplicationList(@PathVariable courseId : Long) : ResponseEntity<List<CourseApplicationResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getApplicationList(courseId))
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable courseId : Long,
        @PathVariable applicationId :Long
    ):ResponseEntity<CourseApplicationResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getApplication(courseId,applicationId))
    }

    @PatchMapping("/{applicationId}")
    fun updateApplicationStatus(
        @PathVariable courseId : Long,
        @PathVariable applicationId :Long,
        @RequestBody updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ):ResponseEntity<CourseApplicationResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.updateApplicationStatus(courseId,applicationId, updateApplicationStatusRequest))
    }

}