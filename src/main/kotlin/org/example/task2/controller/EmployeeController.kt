package org.example.task2.controller

import org.example.task2.dto.EmployeeDto
import org.example.task2.entity.Employee
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.Optional
import java.util.UUID

@RestController
@RequestMapping("/api/employee")
interface EmployeeController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(employee: EmployeeDto): EmployeeDto

    @GetMapping("/id/{id}")
    fun findById(@PathVariable id: UUID): Optional<EmployeeDto>

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: UUID)

    @GetMapping("/position")
    fun getCountEmployeesByPosition(@RequestParam position: String): Int

    @GetMapping
    fun findAll(): List<EmployeeDto>

    @GetMapping("/90days")
    fun findEmployeesWorkedMoreThan90Days(): List<EmployeeDto>

    @DeleteMapping("/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteByName(@RequestParam name: String)

    @DeleteMapping("/all/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllByName(@RequestParam name: String)

    @PutMapping("/{id}")
    fun update(employee: EmployeeDto, @PathVariable id: UUID): EmployeeDto

}