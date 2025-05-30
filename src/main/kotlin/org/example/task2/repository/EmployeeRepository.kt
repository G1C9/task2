package org.example.task2.repository

import org.example.task2.entity.Employee
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface EmployeeRepository {

    fun save(employee: Employee): Employee

    fun findById(id: UUID): Optional<Employee>

    fun deleteById(id: UUID)

    fun findAllByPosition(position: String): List<Employee>

}