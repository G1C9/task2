package org.example.task2.repository.jooq

import org.example.task2.Tables.EMPLOYEE
import org.example.task2.constant.EntitiesConstant
import org.example.task2.entity.Employee
import org.example.task2.exception.IdNotFoundException
import org.example.task2.repository.EmployeeRepository
import org.example.task2.tables.records.EmployeeRecord
import org.jooq.DSLContext
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
@Profile("jooq")
class JooqEmployeeRepository(
    val dslContext: DSLContext,
    //val jooqEmployeeMapper: JooqEmployeeMapper
): EmployeeRepository {

    override fun save(employee: Employee): Employee {
//        val employeeRecord = dslContext.insertInto(
//            EMPLOYEE, EMPLOYEE.ID, EMPLOYEE.NAME, EMPLOYEE.SALARY, EMPLOYEE.DEPARTMENT_ID
//        ).values(employee.id, employee.name, employee.salary, employee.department?.id)
//            .onDuplicateKeyUpdate()
//            .set(EMPLOYEE.NAME, employee.name)
//            .set(EMPLOYEE.SALARY, employee.salary)
//            .set(EMPLOYEE.DEPARTMENT_ID, employee.department?.id)
//            .returning()
//            .fetchOneInto(EmployeeRecord::class.java)
//        return jooqEmployeeMapper.map(employeeRecord)
        return employee
    }

    override fun findById(id: UUID): Optional<Employee> {
//        dslContext.select(
//            EMPLOYEE.ID,
//            EMPLOYEE.NAME,
//            EMPLOYEE.SALARY,
//            EMPLOYEE.DEPARTMENT_ID
//        ).from(EMPLOYEE)
//            .where(EMPLOYEE.ID.eq(id))
//            .fetchOptional()
//            .map { jooqEmployeeMapper.map(it) }
//            .orElseThrow { IdNotFoundException(EntitiesConstant.EMPLOYEE, id)
        TODO("Not yet implemented")
    }

    override fun deleteById(id: UUID) {
//        dslContext.deleteFrom(EMPLOYEE)
//            .where(EMPLOYEE.ID.eq(id))
//            .execute()
    }

    override fun findAllByPosition(position: String): List<Employee> {
//        dslContext.select(
//            EMPLOYEE.ID,
//            EMPLOYEE.NAME,
//            EMPLOYEE.SALARY,
//            EMPLOYEE.DEPARTMENT_ID
//        ).from(EMPLOYEE)
//            .innerJoin(DEPARTMENT).on(EMPLOYEE.DEPARTMENT_ID.eq(DEPARTMENT.ID))
//            .where(DEPARTMENT.POSITION.eq(position))
//            .fetch()
//            .map { jooqEmployeeMapper.map(it) }
        TODO("Not yet implemented")
    }

}