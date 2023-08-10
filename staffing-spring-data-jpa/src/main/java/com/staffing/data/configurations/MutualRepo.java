package com.staffing.data.configurations;

import com.google.common.reflect.ClassPath;
import com.staffing.data.jpa_repos.DepartmentRepository;
import com.staffing.data.jpa_repos.EmployeeRepository;
import com.staffing.data.jpa_repos.ShiftRepository;
import com.staffing.instance.Department;
import com.staffing.instance.Employee;
import com.staffing.instance.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class MutualRepo {
    private String path = "com.staffing.instance";
    private final ShiftRepository shiftRepo;
    private final DepartmentRepository departmentRepo;
    private final EmployeeRepository employeeRepo;

    @Autowired
    public MutualRepo(ShiftRepository shiftRepo, DepartmentRepository departmentRepo, EmployeeRepository employeeRepo) {
        this.shiftRepo = shiftRepo;
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
    }

    private Set<Class> findAllInstanceClasses(String packageName) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(packageName))
                .map(clazz -> clazz.load())
                .collect(Collectors.toSet());
    }

    public String parseJsonIntoObjects(List<HashMap<?, ?>> jsons) {
        var myString = "";
        for (HashMap<?, ?> json : jsons) {
            try {
                List<Set<String>> qualifiedFieldMatches = new ArrayList<>();
                Set<Class> allProjectInstanceClasses = findAllInstanceClasses(this.path);
                for (Class instanceClass : allProjectInstanceClasses) {
                    Field[] declaredFields = instanceClass.getDeclaredFields();
                    Set<String> classNameInstances =
                            Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toSet());
                    Set<String> objectIncomingInstances = new HashSet<>();
                    Map<String, String> map = new HashMap<>();
                    for (Map.Entry<?, ?> entry : json.entrySet()) {
                        String key = entry.getKey().toString();
                        String value = entry.getValue().toString();
                        map.put(key,value);
                        objectIncomingInstances.add(key);
                        myString = String.format("key: %s , val: %s", key, entry.getValue().toString());
                    }
                    classNameInstances.retainAll(objectIncomingInstances);
                    if (!classNameInstances.isEmpty()) {
                        qualifiedFieldMatches.add(new HashSet<>(classNameInstances));
                        String className = instanceClass.getSimpleName();
                        for (Map.Entry<String, String> stringEntry : map.entrySet()) {
                            String key = stringEntry.getKey();
                            String value = stringEntry.getValue();
                            Department department = new Department();
                            Employee employee = new Employee();
                            switch (className){
                                case "Department" -> {
                                    if(key.equals("address")) department.setAddress(value);
                                    if(key.equals("description")) department.setDescription(value);

                                }
                                case "Employee" -> {
                                    if(key.equals("name")) employee.setName(value);
                                    if(key.equals("position")) employee.setPosition(value);
                                }
                            }
                            departmentRepo.save(department);
                            departmentRepo.findTopByOrderByIdDesc();
                            employee.setDepartmentId(department.getId());
                            employeeRepo.save(employee);
                            employeeRepo.findByOrderByIdDesc();
                            Shift shift = new Shift();
                            shift.setShiftStart(new Date());
                            shift.setShiftEnd(new Date());
                            shift.setEmployeeId(employee.getId());
                            shiftRepo.save(shift);
                        }


                    }
//                    myString += "\t" + String.format("project classes: %s , classes from map: %s", classNameInstances, objectIncomingInstances);
                }
            } catch (IOException ex) {
            }
        }
        return myString;
    }

}