package com.staffing.data.configurations;

import com.google.common.reflect.ClassPath;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class MutualRepo {
    private String path = "com.staffing.instance";

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
                Set<Class> allProjectInstanceClasses = findAllInstanceClasses(this.path);
                int occurrencesAmount = 0; // count max similar amount while comparing class fields with json fields
                for (Class instanceClass : allProjectInstanceClasses) {
                    Field[] declaredFields = instanceClass.getDeclaredFields();
                    Set<String> classNameInstances =
                            Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toSet());
                    Set<String> objectIncomingInstances = new HashSet<>();
                    for (Map.Entry<?, ?> entry : json.entrySet()) {
                        String key = entry.getKey().toString();
                        objectIncomingInstances.add(key);
                    }
                    //count occurrences
                    for (String instance : objectIncomingInstances) {
                       if (instance.)
                    }
                    myString += "\t" + String.format("project classes: %s , classes from map: %s", classNameInstances, objectIncomingInstances);
                }
            } catch (IOException ex) {}
        }
        return myString;
    }

}