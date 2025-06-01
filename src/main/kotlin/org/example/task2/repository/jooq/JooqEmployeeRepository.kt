package org.example.task2.repository.jooq

import org.example.task2.entity.Department
import org.example.task2.entity.Employee
import org.example.task2.repository.EmployeeRepository
import org.jooq.DSLContext
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID
import org.example.task2.Tables.DEPARTMENTS
import org.example.task2.Tables.EMPLOYEES
import org.example.task2.tables.records.DepartmentsRecord
import org.example.task2.tables.records.EmployeesRecord
import java.time.LocalDate

@Repository
@Profile("jooq")
class JooqEmployeeRepository(
    val dslContext: DSLContext
): EmployeeRepository {

    private fun toEmployee(employeeRecord: EmployeesRecord, departmentRecord: DepartmentsRecord?): Employee {
        return Employee().apply {
            id = employeeRecord.id
            name = employeeRecord.name
            salary = employeeRecord.salary
            dateOfDepartment = employeeRecord.dateOfDepartment
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
            EMPLOYEES, EMPLOYEES.ID, EMPLOYEES.NAME, EMPLOYEES.SALARY, EMPLOYEES.DATE_OF_DEPARTMENT, EMPLOYEES.DEPARTMENT_ID
        ).values(idToSave, employee.name, employee.salary, employee.dateOfDepartment, employee.department?.id)
            .onDuplicateKeyUpdate()
            .set(EMPLOYEES.NAME, employee.name)
            .set(EMPLOYEES.SALARY, employee.salary)
            .set(EMPLOYEES.DATE_OF_DEPARTMENT, employee.dateOfDepartment)
            .set(EMPLOYEES.DEPARTMENT_ID, employee.department?.id)
            .returning()
            .fetchOne() ?: throw IllegalStateException("Failed to save employee"))
        val departmentRecord = employee.department?.id?.let { deptId ->
            dslContext.selectFrom(DEPARTMENTS).where(DEPARTMENTS.ID.eq(deptId)).fetchOne()
        }

        return toEmployee(record, departmentRecord)
    }

    override fun findById(id: UUID): Optional<Employee> {
        val result = dslContext.select(EMPLOYEES.asterisk(), DEPARTMENTS.asterisk())
            .from(EMPLOYEES)
            .leftJoin(DEPARTMENTS).on(EMPLOYEES.DEPARTMENT_ID.eq(DEPARTMENTS.ID))
            .where(EMPLOYEES.ID.eq(id))
            .fetchOne()

        return Optional.ofNullable(result?.let {
            toEmployee(it.into(EMPLOYEES), it.into(DEPARTMENTS))
        })
    }

    override fun deleteById(id: UUID) {
        dslContext.deleteFrom(EMPLOYEES)
            .where(EMPLOYEES.ID.eq(id))
            .execute()
    }

    override fun findAllByPosition(position: String): List<Employee> {
        val results = dslContext.select(EMPLOYEES.asterisk(), DEPARTMENTS.asterisk())
            .from(EMPLOYEES)
            .innerJoin(DEPARTMENTS).on(EMPLOYEES.DEPARTMENT_ID.eq(DEPARTMENTS.ID))
            .where(DEPARTMENTS.POSITION.eq(position))
            .fetch()

        return results.map { record ->
            toEmployee(record.into(EMPLOYEES), record.into(DEPARTMENTS))
        }
    }

    override fun findAll(): List<Employee> {
        val results = dslContext.select(EMPLOYEES.asterisk(), DEPARTMENTS.asterisk())
            .from(EMPLOYEES)
            .leftJoin(DEPARTMENTS).on(EMPLOYEES.DEPARTMENT_ID.eq(DEPARTMENTS.ID))
            .fetch()

        return results.map { record ->
            toEmployee(record.into(EMPLOYEES), record.into(DEPARTMENTS))
        }
    }

    override fun findEmployeesWorkedMoreThan90Days(): List<Employee> {
        val ninetyDaysAgo = LocalDate.now().minusDays(90)

        val results = dslContext.select(EMPLOYEES.asterisk(), DEPARTMENTS.asterisk())
            .from(EMPLOYEES)
            .leftJoin(DEPARTMENTS).on(EMPLOYEES.DEPARTMENT_ID.eq(DEPARTMENTS.ID))
            .where(EMPLOYEES.DATE_OF_DEPARTMENT.lessOrEqual(ninetyDaysAgo))
            .fetch()

        return results.map { record ->
            toEmployee(record.into(EMPLOYEES), record.into(DEPARTMENTS))
        }
    }

    override fun deleteByDepartmentName(id: UUID, name: String) {
        dslContext.deleteFrom(EMPLOYEES)
            .where(EMPLOYEES.ID.eq(id)
                .and(EMPLOYEES.DEPARTMENT_ID.`in`(
                    dslContext.select(DEPARTMENTS.ID)
                        .from(DEPARTMENTS)
                        .where(DEPARTMENTS.NAME.eq(name))
                )))
            .execute()
    }

    override fun deleteAllByDepartmentName(name: String) {
        dslContext.deleteFrom(EMPLOYEES)
            .where(EMPLOYEES.DEPARTMENT_ID.`in`(
                dslContext.select(DEPARTMENTS.ID)
                    .from(DEPARTMENTS)
                    .where(DEPARTMENTS.NAME.eq(name))
            ))
            .execute()
    }

}