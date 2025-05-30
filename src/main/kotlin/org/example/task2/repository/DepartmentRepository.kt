package org.example.task2.repository

import org.example.task2.entity.Department
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface DepartmentRepository {

    fun deleteAll()

    fun findById(id: UUID?): Optional<Department>

}