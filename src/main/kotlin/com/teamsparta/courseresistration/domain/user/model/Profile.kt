package com.teamsparta.courseresistration.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable  /**다른 Entity에 종속될 수 있음 ( JPA : 값 타입으로 인식 )*/
class Profile(
    @Column(name="nickname",nullable=false)
    var nickname: String,
) {



}
