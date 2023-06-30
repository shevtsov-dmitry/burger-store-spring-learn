package com.burger_store.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/assembleBurger")
public class IngredientController {

    @GetMapping
    public String showForm(){
    // TODO write logic to contain choosen elements into List

        return "assembleBurger";
    }

    @PostMapping
    public String process(
            // TODO INSERT PARAMS
    ){
        // TODO write logic to send assembled object into order form
        return "redirect";
    }
}
