package org.example.task2.repository.jooq

import org.example.task2.entity.Department
import org.example.task2.repository.DepartmentRepository
import org.jooq.DSLContext
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
@Profile("jooq")
class JooqDepartmentRepository(
    private val dslContext: DSLContext
): DepartmentRepository {

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID?): Optional<Department> {
        TODO("Not yet implemented")
    }

}