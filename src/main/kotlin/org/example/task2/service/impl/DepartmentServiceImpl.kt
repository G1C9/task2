package org.example.task2.service.impl

import org.example.task2.repository.DepartmentRepository
import org.example.task2.service.DepartmentService
import org.springframework.stereotype.Service

@Service
class DepartmentServiceImpl(
//    private val departmentRepository: DepartmentRepository
): DepartmentService {

    override fun deleteAll() {
//        departmentRepository.deleteAll()
    }

}

//target
//связные сущности
//deleteAll