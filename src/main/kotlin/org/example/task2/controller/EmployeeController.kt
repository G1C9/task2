package org.example.task2.controller

import org.example.task2.dto.EmployeeDto
import org.example.task2.entity.Employee
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/employee")
interface EmployeeController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(employee: EmployeeDto): EmployeeDto

    @GetMapping("/id/{id}")
    fun findById(@PathVariable id: UUID): Employee

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: UUID)

    @GetMapping("/position")
    fun getCountEmployeesByPosition(@RequestParam position: String): Int

}