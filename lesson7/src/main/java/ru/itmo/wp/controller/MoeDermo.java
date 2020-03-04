package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoeDermo {

    @GetMapping("/exampleDotCom")
    public String exampleDotCom() {
        return "ExampleDotCom";
    }

    @GetMapping("/circular1")
    public String circular1() {
        return "Circular1";
    }

    @GetMapping("/circular2")
    public String circular2() {
        return "Circular2";
    }
    @GetMapping("/circular3")
    public String circular3() {
        return "Circular3";
    }
}
