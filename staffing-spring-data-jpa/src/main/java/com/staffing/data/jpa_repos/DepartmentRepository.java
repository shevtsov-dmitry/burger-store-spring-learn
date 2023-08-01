package com.staffing.data.jpa_repos;

import com.example.jpa_test.instance.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
