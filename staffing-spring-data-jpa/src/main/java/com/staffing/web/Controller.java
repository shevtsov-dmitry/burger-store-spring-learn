package com.staffing.web;

import com.staffing.data.jpa_repos.DepartmentRepository;
import com.staffing.data.jpa_repos.EmployeeRepository;
import com.staffing.data.jpa_repos.ShiftRepository;
import com.staffing.instance.Shift;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import
@RestController
@RequestMapping("/")


public class Controller {
    public Logger log = LoggerFactory.getLogger(this.getClass());

    private final ShiftRepository shiftRepo;
    private final DepartmentRepository departmentRepo;
    private final EmployeeRepository employeeRepo;

    @Autowired
    public Controller(ShiftRepository shiftRepo,
                      DepartmentRepository departmentRepo,
                      EmployeeRepository employeeRepo) {
        this.shiftRepo = shiftRepo;
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveNewShift(@RequestBody List<HashMap<?,?>> jsonList) {

        return ResponseEntity.ok("Success.");
    }

//    @GetMapping
//    public String form() {
//        Shift shift = shiftRepo.getShiftById(2);
//        String string = shift.getDepartment();
//        return string;
//    }


    /*@Bean public CommandLineRunner runner(ShiftRepository shiftRepository) { return args -> {
            shiftRepository.save(new Shift("Milovi", new Date(), new Date()));
            shiftRepository.save(new Shift("raed", new Date(), new Date()));
            shiftRepository.save(new Shift("Off", new Date(), new Date()));
            shiftRepository.save(new Shift("Archi", new Date(), new Date()));
            shiftRepository.save(new Shift("Chupiter", new Date(), new Date()));
        };
    */
}