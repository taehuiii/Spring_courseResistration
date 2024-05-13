package com.teamsparta.courseresistration.domain.lecture.repository

import com.teamsparta.courseresistration.domain.lecture.model.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureRepository: JpaRepository<Lecture, Long> {
}
}