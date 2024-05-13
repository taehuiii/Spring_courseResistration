package com.teamsparta.courseresistration.domain.user.model

import com.teamsparta.courseresistration.domain.courseapplication.model.CourseApplication
import com.teamsparta.courseresistration.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name="app_user") //user는 예약어라 이름 따로 지어줘야함
class User(

    @Column(name="email", nullable=false)
    var email:String,

    @Column(name="password", nullable=false)
    var password:String,

    @Embedded /**@Embeddable(값타입)을, 다른 Entity에 종속시킬 때( 사용할 때 )*/
    val profile: Profile,

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable=false)
    var role: UserRole,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
val courseApplications: MutableList<CourseApplication> = mutableListOf()


) {
  @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY) //db에 생성을 맡기는 전략
    var id: Long? =null

}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        nickname = profile.nickname,
        email = email,
        role = role.name
    )
}