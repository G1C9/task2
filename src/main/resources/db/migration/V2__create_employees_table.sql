CREATE TABLE employees (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    salary INT NOT NULL,
    date_of_department DATE NOT NULL,
    department_id   UUID    NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE
)