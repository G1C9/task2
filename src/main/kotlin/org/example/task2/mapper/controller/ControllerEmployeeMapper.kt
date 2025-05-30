package org.example.task2.mapper.controller

import org.example.task2.dto.EmployeeDto
import org.example.task2.entity.Employee
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ControllerEmployeeMapper {

    @Mapping(target = "departmentId", source = "department.id")
    fun map(employee: Employee): EmployeeDto

    @Mapping(target = "department.id", source = "departmentId")
    fun map(employeeDto: EmployeeDto): Employee

}