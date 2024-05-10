package com.teamsparta.courseresistration.domain.exception

import java.lang.RuntimeException

data class ModelNotFoundException (val modelName: String, val id:Long?):
        RuntimeException("Model $modelName not found with given id: $id")