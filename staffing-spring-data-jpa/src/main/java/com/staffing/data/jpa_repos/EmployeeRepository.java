package com.staffing.data.jpa_repos;

import com.example.jpa_test.instance.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
