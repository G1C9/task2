package org.example.task2.service

import org.example.task2.entity.Department
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
interface DepartmentService {

    fun deleteById(id: UUID)

    fun findById(id: UUID?): Optional<Department>

    fun create(department: Department): Department

}