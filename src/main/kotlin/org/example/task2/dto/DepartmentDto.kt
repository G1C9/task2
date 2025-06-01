package org.example.task2.dto

import java.util.UUID

data class DepartmentDto(
    val id: UUID,
    val name: String,
    val position: String
)
