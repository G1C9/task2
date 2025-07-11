package org.example.task2.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "employees")
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var salary: Int? = null

    @Column(name = "date_of_department", nullable = false)
    var dateOfDepartment: LocalDate? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "department_id", nullable = false)
    var department: Department? = null

}