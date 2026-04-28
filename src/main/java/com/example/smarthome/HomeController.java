package com.example.smarthome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/lighting")
    public String lighting() {
        return "lighting";
    }

    @GetMapping("/climate")
    public String climate() {
        return "climate";
    }

    @GetMapping("/security")
    public String security() {
        return "security";
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }
}
