package org.example.task2.controller.impl

import org.example.task2.controller.DepartmentController
import org.example.task2.service.DepartmentService
import org.springframework.web.bind.annotation.RestController

@RestController
class DepartmentControllerImpl(
    private val departmentService: DepartmentService
): DepartmentController {

    override fun deleteAll() {
        departmentService.deleteAll()
    }

}