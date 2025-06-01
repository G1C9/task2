package org.example.task2.service

import org.example.task2.entity.Employee
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
interface EmployeeService {

    fun create(employee: Employee): Employee

    fun findById(employeeId: UUID): Optional<Employee>

    fun deleteById(employeeId: UUID)

    fun getCountEmployeesByPosition(position: String): Int

    fun findAll(): List<Employee>

    fun findEmployeesWorkedMoreThan90Days(): List<Employee>

    fun deleteByName(name: String)

    fun deleteAllByName(name: String)

    fun update(employee: Employee, id: UUID): Employee

}