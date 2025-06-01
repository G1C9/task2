package org.example.task2.service.impl

import org.example.task2.entity.Employee
import org.example.task2.repository.EmployeeRepository
import org.example.task2.service.EmployeeService
import org.springframework.stereotype.Service
import java.util.UUID
import org.example.task2.constant.EntitiesConstant.EMPLOYEE
import org.example.task2.exception.IdNotFoundException
import java.util.Optional

@Service
class EmployeeServiceImpl(
    private val employeeRepository: EmployeeRepository
): EmployeeService {

    val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    override fun create(employee: Employee): Employee {
        logger.info("CREATE $EMPLOYEE")
        return employeeRepository.save(employee)
    }

    override fun findById(id: UUID): Optional<Employee> {
        logger.info("GET $EMPLOYEE WITH $id")
        return employeeRepository.findById(id).also { optional ->
            if (optional.isEmpty) {
                logger.error("$EMPLOYEE NOT FOUND WITH $id")
            }
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

    override fun findAll(): List<Employee> {
        logger.info("FIND ALL EMPLOYEES")
        return employeeRepository.findAll()
    }

    override fun findEmployeesWorkedMoreThan90Days(): List<Employee> {
        logger.info("FIND ALL EMPLOYEES WHO WORKED MORE THAN 90 DAYS")
        return employeeRepository.findEmployeesWorkedMoreThan90Days()
    }

    override fun deleteByName(name: String) {
        logger.info("DELETE EMPLOYEE BY DEPARTMENT NAME")
        employeeRepository.deleteByName(name)
    }

    override fun deleteAllByName(name: String) {
        logger.info("DELETE ALL EMPLOYEES BY DEPARTMENT NAME")
        employeeRepository.deleteAllByName(name)
    }

    override fun update(employee: Employee, id: UUID): Employee {
        return employeeRepository.findById(id)
            .map{ existingEmployee ->
                val updatedEmployee = existingEmployee.apply {
                    name = employee.name
                    salary = employee.salary
                    dateOfDepartment = employee.dateOfDepartment
                    department = employee.department
                }.also {
                    logger.info("UPDATE EMPLOYEE WITH ID: $id")
                }
                employeeRepository.save(updatedEmployee)
            }
            .orElseThrow{ IdNotFoundException(EMPLOYEE, id) }
    }

}