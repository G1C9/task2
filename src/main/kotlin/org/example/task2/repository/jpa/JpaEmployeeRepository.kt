package org.example.task2.repository.jpa

import org.example.task2.entity.Employee
import org.example.task2.repository.EmployeeRepository
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Profile("jpa")
interface JpaEmployeeRepository: JpaRepository<Employee, UUID>, EmployeeRepository {

    @Query("select e from Employee e " +
            "where e.department.position = :position")
    override fun findAllByPosition(@Param("position") position: String): List<Employee>

    @Query(value = "SELECT * FROM employees e WHERE (CURRENT_DATE - e.date_of_department) > 90",
        nativeQuery = true)
    override fun findEmployeesWorkedMoreThan90Days(): List<Employee>

    @Modifying
    @Query("delete from Employee e " +
            "where e.id =:id and e.department.name = :name")
   override fun deleteByDepartmentName(@Param("id") id: UUID, @Param("name") name: String)

    @Modifying
    @Query("delete from Employee e " +
            "where e.department.name = :name")
    override fun deleteAllByDepartmentName(@Param("name") name: String)

}