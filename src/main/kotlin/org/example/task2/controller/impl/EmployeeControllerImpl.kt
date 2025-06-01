package org.example.task2.controller.impl

import org.example.task2.controller.EmployeeController
import org.example.task2.dto.EmployeeDto
import org.example.task2.entity.Department
import org.example.task2.entity.Employee
import org.example.task2.service.DepartmentService
import org.example.task2.service.EmployeeService
import org.springframework.web.bind.annotation.RestController
import java.util.Optional
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@RestController
class EmployeeControllerImpl(
    private val employeeService: EmployeeService,
    private val departmentService: DepartmentService,
): EmployeeController {

    private fun entityToDto(employee: Employee): EmployeeDto {
        return EmployeeDto(
            id = employee.id!!,
            name = employee.name!!,
            salary = employee.salary!!,
            dateOfDepartment = employee.dateOfDepartment!!,
            departmentId = employee.department!!.id!!
        )
    }

    private fun dtoToEntity(dto: EmployeeDto, department: Department): Employee {
        return Employee().apply {
            name = dto.name
            salary = dto.salary
            dateOfDepartment = dto.dateOfDepartment
            this.department = department
        }
    }

    override fun create(employeeDto: EmployeeDto): EmployeeDto {
        val department = departmentService.findById(employeeDto.departmentId).getOrNull()
            ?: throw IllegalArgumentException("Department not found for ID: ${employeeDto.departmentId}")

        val newEntity = dtoToEntity(employeeDto, department)
        val result = employeeService.create(newEntity)
        return entityToDto(result)

    }

    override fun findById(id: UUID): Optional<EmployeeDto> {
        return employeeService.findById(id).map{entityToDto(it)}
    }

    override fun deleteById(id: UUID) {
        employeeService.deleteById(id)
    }

    override fun getCountEmployeesByPosition(position: String): Int {
        return employeeService.getCountEmployeesByPosition(position)
    }

    override fun findAll(): List<EmployeeDto> {
        return employeeService.findAll().map { entityToDto(it) }
    }

    override fun findEmployeesWorkedMoreThan90Days(): List<EmployeeDto> {
        return employeeService.findEmployeesWorkedMoreThan90Days().map { entityToDto(it) }
    }

    override fun deleteByDepartmentName(id: UUID, name: String) {
        employeeService.deleteByDepartmentName(id, name)
    }

    override fun deleteAllByDepartmentName(name: String) {
        employeeService.deleteAllByDepartmentName(name)
    }

    override fun update(employeeDto: EmployeeDto, id: UUID): EmployeeDto {
        val department = departmentService.findById(employeeDto.departmentId).getOrNull()
            ?: throw IllegalArgumentException("Department not found for ID: ${employeeDto.departmentId}")

        val updatedEntity = dtoToEntity(employeeDto, department)
        val result = employeeService.update(updatedEntity, id)
        return entityToDto(result)
    }

}