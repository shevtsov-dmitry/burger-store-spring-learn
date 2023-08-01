package com.staffing.data.jpa_repos;

import com.staffing.instance.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
