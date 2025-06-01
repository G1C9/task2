//package org.example.task2.repository.jooq
//
//import org.example.task2.Tables.DEPARTMENTS
//import org.example.task2.entity.Department
//import org.example.task2.repository.DepartmentRepository
//import org.jooq.DSLContext
//import org.springframework.context.annotation.Profile
//import org.springframework.stereotype.Repository
//import java.util.Optional
//import java.util.UUID
//import org.example.task2.tables.records.DepartmentsRecord
//
//@Repository
//@Profile("jooq")
//class JooqDepartmentRepository(
//    private val dslContext: DSLContext
//): DepartmentRepository {
//
//    private fun toDepartment(departmentRecord: DepartmentsRecord): Department {
//        return Department().apply {
//            id = departmentRecord.id
//            name = departmentRecord.name
//            position = departmentRecord.position
//        }
//    }
//
//
//    override fun findById(id: UUID?): Optional<Department> {
//        return Optional.ofNullable(
//            dslContext.selectFrom(DEPARTMENTS)
//                .where(DEPARTMENTS.ID.eq(id))
//                .fetchOneInto(DepartmentsRecord::class.java)
//                ?.map { toDepartment(it as DepartmentsRecord) }
//        )
//    }
//
//}