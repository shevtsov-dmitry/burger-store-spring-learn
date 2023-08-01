package com.staffing.data.jpa_repos;

import com.example.jpa_test.instance.Shift;
import org.springframework.data.repository.CrudRepository;

public interface ShiftRepository extends CrudRepository<Shift, Long> {
     Shift getShiftById(long id);
}
