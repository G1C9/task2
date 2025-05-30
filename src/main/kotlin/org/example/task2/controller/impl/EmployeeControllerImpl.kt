package org.example.task2.controller.impl

import org.example.task2.controller.EmployeeController
import org.example.task2.dto.EmployeeDto
import org.example.task2.entity.Employee
import org.example.task2.repository.DepartmentRepository
import org.example.task2.service.EmployeeService
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@RestController
class EmployeeControllerImpl(
    private val employeeService: EmployeeService,
    private val departmentRepository: DepartmentRepository,
): EmployeeController {

    override fun create(employee: EmployeeDto): EmployeeDto {
        val department = departmentRepository.findById(employee.departmentId).getOrNull()
            ?: throw IllegalArgumentException("Department not found for ID: ${employee.departmentId}")

        val newEntity = Employee().apply {
            name = employee.name
            salary = employee.salary
            this.department = department
        }

        val result = employeeService.create(newEntity)

        val depId = result.department!!.id!!
         return EmployeeDto(
             id = result.id!!,
             name = result.name!!,
             salary = result.salary!!,
             departmentId = depId
         )
    }

    override fun findById(id: UUID): Employee {
        return employeeService.findById(id)
    }

    override fun deleteById(id: UUID) {
        employeeService.deleteById(id)
    }

    override fun getCountEmployeesByPosition(position: String): Int {
        return employeeService.getCountEmployeesByPosition(position)
    }

}