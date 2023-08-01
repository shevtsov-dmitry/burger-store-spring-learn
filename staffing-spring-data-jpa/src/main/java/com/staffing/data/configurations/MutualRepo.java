package com.staffing.data.configurations;

import com.google.common.reflect.ClassPath;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class MutualRepo {
    /*public static <T> List<T> getClassFieldsAsList(T object){

    }*/

    String path = "com.example.jpa_test.instance";
    private Set<Class> findAllInstanceClasses(String packageName) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(packageName))
                .map(clazz -> clazz.load())
                .collect(Collectors.toSet());
    }

    public List<?> parseJsonIntoObjects()
}
