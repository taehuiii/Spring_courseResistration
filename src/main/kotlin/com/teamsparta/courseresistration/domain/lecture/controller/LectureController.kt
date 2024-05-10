package com.teamsparta.courseresistration.domain.lecture.controller

import com.teamsparta.courseresistration.domain.lecture.dto.AddLectureRequest
import com.teamsparta.courseresistration.domain.lecture.dto.LectureResponse
import com.teamsparta.courseresistration.domain.lecture.dto.UpdateLectureRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/course/{courseId}/lectures")
@RestController
class LectureController {

    //전체 목록조회
    @GetMapping()
    fun getLectureList(@PathVariable courseId: Long ) : ResponseEntity<List<LectureResponse>>{
        //따로 요청param값이 필요없었던 course전체목록조회와 달리, lecture전체목록조회는 courseId가 필요하다 ( course의 하위계층이니까 ) -> 모든 crud에서 다
        TODO("not implemented")
    }

    //lecture 단건 조회
    @GetMapping("/{lectureID}")
    fun getLecture(@PathVariable courseId: Long ,@PathVariable lectureId : Long ): ResponseEntity<LectureResponse>{
        //courseID, lectureId 둘다 받음.
        TODO("not implemented")
    }

    //lecture 생성
    @PostMapping()
    fun addLecture(@PathVariable courseId: Long ,@RequestBody addLectureRequest: AddLectureRequest) : ResponseEntity<LectureResponse>{
        TODO("not implemented")
    }


    //Lecture 수정
    @PutMapping("/{lectureId}")
    fun updateLecture(
        @PathVariable courseId: Long,
        @PathVariable lectureID:Long,
        @RequestBody updateLectureRequest: UpdateLectureRequest) : ResponseEntity<LectureResponse>{
        TODO("not implemented")
    }

    @DeleteMapping("/{lectureId}")
    fun RemoveLecture(
        @PathVariable courseId: Long,@PathVariable lectureID:Long, ) : ResponseEntity<Unit>{ //Unit인 이유 ?
        TODO("not implemented")
    }


}