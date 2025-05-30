package org.example.task2.service

import org.example.task2.entity.Employee
import org.springframework.stereotype.Service
import java.util.UUID

@Service
interface EmployeeService {

    fun create(employee: Employee): Employee

    fun findById(employeeId: UUID): Employee

    fun deleteById(employeeId: UUID)

    fun getCountEmployeesByPosition(position: String): Int

}