package org.example.task2.service.impl

import jakarta.transaction.Transactional
import org.example.task2.constant.EntitiesConstant.DEPARTMENT
import org.example.task2.entity.Department
import org.example.task2.exception.IdNotFoundException
import org.example.task2.repository.DepartmentRepository
import org.example.task2.service.DepartmentService
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class DepartmentServiceImpl(
    private val departmentRepository: DepartmentRepository
): DepartmentService {

    val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    @Transactional
    override fun deleteById(id: UUID) {
        logger.info("DELETE $DEPARTMENT WITH $id")
        departmentRepository.deleteById(id)
    }

    override fun findById(id: UUID?): Optional<Department> {
        return departmentRepository.findById(id).also { optional ->
            if (optional.isEmpty) {
                logger.error("$DEPARTMENT NOT FOUND WITH $id")
                IdNotFoundException(DEPARTMENT, id)
            }
            else {
                logger.info("GET $DEPARTMENT WITH $id")
            }
        }
    }

    @Transactional
    override fun create(department: Department): Department {
        logger.info("CREATE $DEPARTMENT")
        return departmentRepository.save(department)
    }

}