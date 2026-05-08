package com.example.smarthome;

import com.example.smarthome.model.Device;
import com.example.smarthome.model.User;
import com.example.smarthome.service.DeviceService;
import com.example.smarthome.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserToModel(Model model, HttpSession session) {
        model.addAttribute("loggedInUsername", session.getAttribute("loggedInUsername"));
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        if (userService.isValidLogin(username, password)) {
            session.setAttribute("loggedInUsername", username);
            return "redirect:/";
        }
        model.addAttribute("loginError", "Invalid username or password");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/security")
    public String security() {
        return "security";
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }


    @GetMapping("/lighting")
    public String lighting(Model model, HttpSession session) {
        String username = (String) session.getAttribute("loggedInUsername");

        if (username != null) {
            User user = userService.findByUsername(username);
            List<Device> lights = deviceService.getDeviceByUserAndType(user.getId(), "light");
            model.addAttribute("devices", lights);
        }

        return "lighting";
    }

    @GetMapping("/climate")
    public String climate(Model model, HttpSession session) {
        String username = (String) session.getAttribute("loggedInUsername");

        if (username != null) {
            User user = userService.findByUsername(username);
            List<Device> thermostats = deviceService.getDeviceByUserAndType(user.getId(), "thermostat");
            model.addAttribute("devices", thermostats);
        }

        return "climate";
    }

    // --- Registration: save new user to database ---

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password) {
        userService.registerUser(username, email, password);
        return "redirect:/login";
    }

    // --- Device management ---

    @PostMapping("/devices/add")
    public String addDevice(@RequestParam String name,
                            @RequestParam String type,
                            HttpSession session) {
        String username = (String) session.getAttribute("loggedInUsername");

        if (username == null) {
            return "redirect:/login";
        }

        User user = userService.findByUsername(username);
        deviceService.addDevice(name, type, user);
        return "redirect:/settings";
    }

    @PostMapping("/devices/toggle")
    public String toggleDevice(@RequestParam Long deviceId) {
        deviceService.toggleDevice(deviceId);
        return "redirect:/settings";
    }

    @PostMapping("/devices/delete")
    public String deleteDevice(@RequestParam Long deviceId) {
        deviceService.deleteDevice(deviceId);
        return "redirect:/settings";
    }

}