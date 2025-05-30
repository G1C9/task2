package org.example.task2.repository.jooq

import org.example.task2.entity.Department
import org.example.task2.repository.DepartmentRepository
import org.example.task2.tables.records.DepartmentRecord
import org.jooq.DSLContext
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID
import org.example.task2.Tables.DEPARTMENT

@Repository
@Profile("jooq")
class JooqDepartmentRepository(
    private val dslContext: DSLContext
): DepartmentRepository {

    private fun toDepartment(departmentRecord: DepartmentRecord): Department {
        return Department().apply {
            id = departmentRecord.id
            name = departmentRecord.name
            position = departmentRecord.position
        }
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID?): Optional<Department> {
        return Optional.ofNullable(
            dslContext.selectFrom(DEPARTMENT)
                .where(DEPARTMENT.ID.eq(id))
                .fetchOneInto(DepartmentRecord::class.java)
                ?.map { toDepartment(it as DepartmentRecord) }
        )
    }

}