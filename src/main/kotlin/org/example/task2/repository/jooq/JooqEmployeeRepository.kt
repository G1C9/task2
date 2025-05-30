package org.example.task2.repository.jooq

import org.example.task2.Tables.DEPARTMENT
import org.example.task2.Tables.EMPLOYEE
import org.example.task2.entity.Department
import org.example.task2.entity.Employee
import org.example.task2.repository.EmployeeRepository
import org.example.task2.tables.records.EmployeeRecord
import org.example.task2.tables.records.DepartmentRecord
import org.jooq.DSLContext
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
@Profile("jooq")
class JooqEmployeeRepository(
    val dslContext: DSLContext
): EmployeeRepository {

    private fun toEmployee(employeeRecord: EmployeeRecord, departmentRecord: DepartmentRecord?): Employee {
        return Employee().apply {
            id = employeeRecord.id
            name = employeeRecord.name
            salary = employeeRecord.salary
            department = Department().apply {
                id = departmentRecord?.id
                name = departmentRecord?.name
                position = departmentRecord?.position
            }
        }
    }

    override fun save(employee: Employee): Employee {
        val idToSave = employee.id ?: UUID.randomUUID()
        val record = (dslContext.insertInto(
            EMPLOYEE, EMPLOYEE.ID, EMPLOYEE.NAME, EMPLOYEE.SALARY, EMPLOYEE.DEPARTMENT_ID
        ).values(idToSave, employee.name, employee.salary, employee.department?.id)
            .onDuplicateKeyUpdate()
            .set(EMPLOYEE.NAME, employee.name)
            .set(EMPLOYEE.SALARY, employee.salary)
            .set(EMPLOYEE.DEPARTMENT_ID, employee.department?.id)
            .returning()
            .fetchOne() ?: throw IllegalStateException("Failed to save employee"))
        val departmentRecord = employee.department?.id?.let { deptId ->
            dslContext.selectFrom(DEPARTMENT).where(DEPARTMENT.ID.eq(deptId)).fetchOne()
        }

        return toEmployee(record, departmentRecord)
    }

    override fun findById(id: UUID): Optional<Employee> {
        val result = dslContext.select(EMPLOYEE.asterisk(), DEPARTMENT.asterisk())
            .from(EMPLOYEE)
            .leftJoin(DEPARTMENT).on(EMPLOYEE.DEPARTMENT_ID.eq(DEPARTMENT.ID))
            .where(EMPLOYEE.ID.eq(id))
            .fetchOne()

        return Optional.ofNullable(result?.let {
            toEmployee(it.into(EMPLOYEE), it.into(DEPARTMENT))
        })
    }

    override fun deleteById(id: UUID) {
        dslContext.deleteFrom(EMPLOYEE)
            .where(EMPLOYEE.ID.eq(id))
            .execute()
    }

    override fun findAllByPosition(position: String): List<Employee> {
        val results = dslContext.select(EMPLOYEE.asterisk(), DEPARTMENT.asterisk())
            .from(EMPLOYEE)
            .innerJoin(DEPARTMENT).on(EMPLOYEE.DEPARTMENT_ID.eq(DEPARTMENT.ID))
            .where(DEPARTMENT.POSITION.eq(position))
            .fetch()

        return results.map { record ->
            toEmployee(record.into(EMPLOYEE), record.into(DEPARTMENT))
        }
    }

}