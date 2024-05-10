package com.teamsparta.courseresistration.domain.courseapplication.controller

import com.teamsparta.courseresistration.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseresistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseresistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/courses/{courseId}/applications")
@RestController
class CourseApplicationController {

    @PostMapping
    fun applyCourse(@PathVariable courseId : Long, @RequestBody applyCourseRequest: ApplyCourseRequest) : ResponseEntity<CourseApplicationResponse>{
        TODO("Not yet implemented")
    }

    @GetMapping()
    fun getApplicationList(@PathVariable courseId : Long) : ResponseEntity<List<CourseApplicationResponse>>{
        TODO("Not yet implemented")
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable courseId : Long,
        @PathVariable applicationId :Long
    ):ResponseEntity<CourseApplicationResponse>{
        TODO("Not yet implemented")
    }

    @PatchMapping("/{applicationId}")
    fun updateApplicationStatus(
        @PathVariable courseId : Long,
        @PathVariable applicationId :Long,
        @RequestBody updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ):ResponseEntity<CourseApplicationResponse>{
        TODO("Not yet implemented")
    }

}