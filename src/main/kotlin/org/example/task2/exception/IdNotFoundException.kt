package org.example.task2.exception

import java.lang.RuntimeException
import java.util.UUID

class IdNotFoundException(entity: String, id: UUID): RuntimeException(
    "$entity not found with id: $id"
)