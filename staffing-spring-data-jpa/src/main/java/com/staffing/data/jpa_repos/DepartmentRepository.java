package com.staffing.data.jpa_repos;

import com.staffing.instance.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
