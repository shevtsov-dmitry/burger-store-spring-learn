CREATE TABLE IF NOT EXISTS department
(
    id          SERIAL PRIMARY KEY,
    address     VARCHAR(500),
    description TEXT
);

CREATE TABLE IF NOT EXISTS employee
(
    id            SERIAL PRIMARY KEY,
    name          varchar(15),
    position      varchar(15),
    department_id int references department (id)

);

CREATE TABLE IF NOT EXISTS shift
(
    id            SERIAL PRIMARY KEY,
    shift_start   DATE,
    shift_end     DATE,
    employee_id int REFERENCES employee (id)
);




