package org.example.task2.service.impl

import org.example.task2.entity.Employee
import org.example.task2.repository.EmployeeRepository
import org.example.task2.service.EmployeeService
import org.springframework.stereotype.Service
import java.util.UUID
import org.example.task2.constant.EntitiesConstant.EMPLOYEE
import org.example.task2.exception.IdNotFoundException

@Service
class EmployeeServiceImpl(
    private val employeeRepository: EmployeeRepository
): EmployeeService {

    val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    override fun create(employee: Employee): Employee {
        logger.info("CREATE $EMPLOYEE")
        return employeeRepository.save(employee)
    }

    override fun findById(id: UUID): Employee {
        logger.info("GET $EMPLOYEE WITH $id")
        val result = employeeRepository.findById(id)
        return result.orElseThrow {
            logger.error("$EMPLOYEE NOT FOUND WITH $id")
            IdNotFoundException(EMPLOYEE, id)
        }
    }

    override fun deleteById(id: UUID) {
        logger.info("DELETE $EMPLOYEE WITH $id")
        employeeRepository.deleteById(id)
    }

    override fun getCountEmployeesByPosition(position: String): Int {
        logger.info("FIND ALL EMPLOYEES BY THIS $position")
        val list = employeeRepository.findAllByPosition(position)
        return list.size
    }

}