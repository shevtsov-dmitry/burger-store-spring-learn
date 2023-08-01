package com.burger_store_jpa.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class MainConfig {
    @GetMapping("/")
    public String showMainForm(){
        return "";
    }

}
