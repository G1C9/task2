package org.example.task2.repository.jpa

import org.example.task2.entity.Department
import org.example.task2.repository.DepartmentRepository
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
@Profile("jpa")
interface JpaDepartmentRepository: JpaRepository<Department, UUID>, DepartmentRepository {

}