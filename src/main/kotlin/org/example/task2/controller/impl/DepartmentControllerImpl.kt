package org.example.task2.controller.impl

import org.example.task2.controller.DepartmentController
import org.example.task2.dto.DepartmentDto
import org.example.task2.entity.Department
import org.example.task2.service.DepartmentService
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class DepartmentControllerImpl(
    private val departmentService: DepartmentService
): DepartmentController {

    private fun entityToDto(department: Department): DepartmentDto {
        return DepartmentDto(
            id = department.id!!,
            name = department.name!!,
            position = department.position!!
        )
    }

    private fun dtoToEntity(dto: DepartmentDto): Department {
        return Department().apply {
            name = dto.name
            position = dto.position
        }
    }

    override fun deleteById(id: UUID) {
        return departmentService.deleteById(id)
    }

    override fun create(departmentDto: DepartmentDto): DepartmentDto {
        val newEntity = dtoToEntity(departmentDto)
        val result = departmentService.create(newEntity)
        return entityToDto(result)
    }

}