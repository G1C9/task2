package org.example.task2.repository.jpa

import org.example.task2.entity.Employee
import org.example.task2.repository.EmployeeRepository
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalTime
import java.util.UUID

@Repository
@Profile("jpa")
interface JpaEmployeeRepository: JpaRepository<Employee, UUID>, EmployeeRepository {

    @Query("select e from Employee e " +
            "join e.department d " +
            "where d.position = :position")
    override fun findAllByPosition(@Param("position") position: String): List<Employee>

    @Query(value = "SELECT * FROM employee e WHERE DATEDIFF(CURRENT_DATE, e.date_of_department) > 90",
        nativeQuery = true)
    override fun findEmployeesWorkedMoreThan90Days(): List<Employee>

    @Query("select e from Employee e " +
            "join e.department d " +
            "where d.name = :name")
   override fun deleteByName(@Param("name") name: String)

    @Query("select e from Employee e " +
            "join e.department d " +
            "where d.name = :name")
    override fun deleteAllByName(@Param("name") name: String)

}