package org.example.task2.dto

import java.time.LocalDate
import java.util.UUID

data class EmployeeDto(
    val id: UUID,
    val name: String,
    val salary: Int,
    var dateOfDepartment: LocalDate,
    val departmentId: UUID?
)
