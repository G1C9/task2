package org.example.task2.dto

import java.util.UUID

data class EmployeeDto(
    val id: UUID,
    val name: String,
    val salary: Int,
    val departmentId: UUID?
)
