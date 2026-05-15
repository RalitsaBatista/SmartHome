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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    private boolean isNotLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUsername") == null;
    }

    @ModelAttribute
    public void addLoggedInUserToModel(Model model, HttpSession session) {
        model.addAttribute("loggedInUsername", session.getAttribute("loggedInUsername"));
    }
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

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
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (username == null || username.isBlank()) {
            model.addAttribute("loginError", "Username is required.");
            return "login";
        }

        if (password == null || password.isBlank()) {
            model.addAttribute("loginError", "Password is required.");
            return "login";
        }

        if (userService.isValidLogin(username.trim(), password)) {
            session.setAttribute("loggedInUsername", username.trim());
            redirectAttributes.addFlashAttribute("successMessage", "Welcome back, " + username.trim() + "!");
            return "redirect:/";
        }

        model.addAttribute("loginError", "Invalid username or password.");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "You have been logged out successfully.");
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/security")
    public String security(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (isNotLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to access Security.");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("loggedInUsername");
        User user = userService.findByUsername(username);

        List<Device> cameras = deviceService.getDeviceByUserAndType(user.getId(), "camera");
        List<Device> locks = deviceService.getDeviceByUserAndType(user.getId(), "lock");
        List<Device> sensors = deviceService.getDeviceByUserAndType(user.getId(), "sensor");

        model.addAttribute("cameras", cameras);
        model.addAttribute("locks", locks);
        model.addAttribute("sensors", sensors);

        return "security";
    }

    @GetMapping("/settings")
    public String settings(HttpSession session, RedirectAttributes redirectAttributes) {
        if (isNotLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to access Settings.");
            return "redirect:/login";
        }

        return "settings";
    }

    @GetMapping("/lighting")
    public String lighting(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (isNotLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to access Lighting.");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("loggedInUsername");
        User user = userService.findByUsername(username);
        List<Device> lights = deviceService.getDeviceByUserAndType(user.getId(), "light");
        model.addAttribute("devices", lights);

        return "lighting";
    }

    @GetMapping("/climate")
    public String climate(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (isNotLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to access Climate.");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("loggedInUsername");
        User user = userService.findByUsername(username);
        List<Device> thermostats = deviceService.getDeviceByUserAndType(user.getId(), "thermostat");
        model.addAttribute("devices", thermostats);

        return "climate";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (username == null || username.isBlank()) {
            model.addAttribute("registerError", "Username is required.");
            return "register";
        }

        if (email == null || email.isBlank()) {
            model.addAttribute("registerError", "Email is required.");
            return "register";
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            model.addAttribute("registerError", "Please enter a valid email address.");
            return "register";
        }

        if (password == null || password.isBlank()) {
            model.addAttribute("registerError", "Password is required.");
            return "register";
        }

        if (password.length() < 8) {
            model.addAttribute("registerError", "Password must be at least 8 characters long.");
            return "register";
        }

        if (userService.usernameExists(username.trim())) {
            model.addAttribute("registerError", "Username is already taken.");
            return "register";
        }

        if (userService.emailExists(email.trim())) {
            model.addAttribute("registerError", "Email is already registered.");
            return "register";
        }

        userService.registerUser(username.trim(), email.trim(), password);
        redirectAttributes.addFlashAttribute("successMessage", "Account created successfully. You can now log in.");
        return "redirect:/login";
    }

    // --- Device management ---

    @PostMapping("/devices/add")
    public String addDevice(@RequestParam String name,
                            @RequestParam String type,
                            @RequestParam(required = false) String room,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        if (isNotLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in before adding devices.");
            return "redirect:/login";
        }

        if (name == null || name.isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Device name is required.");
            return "redirect:/settings";
        }

        if (type == null || type.isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Device type is required.");
            return "redirect:/settings";
        }

        String normalizedType = type.trim().toLowerCase();

        if (!List.of("light", "thermostat", "camera", "lock", "sensor").contains(normalizedType)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Unsupported device type.");
            return "redirect:/settings";
        }

        String normalizedRoom = room == null || room.isBlank() ? "Unassigned" : room.trim();

        String username = (String) session.getAttribute("loggedInUsername");
        User user = userService.findByUsername(username);
        deviceService.addDevice(name.trim(), normalizedType, normalizedRoom, user);

        redirectAttributes.addFlashAttribute("successMessage", "Device added successfully.");
        return "redirect:/settings";
    }

    @PostMapping("/devices/toggle")
    public String toggleDevice(@RequestParam Long deviceId,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (isNotLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in before changing devices.");
            return "redirect:/login";
        }

        deviceService.toggleDevice(deviceId);
        redirectAttributes.addFlashAttribute("successMessage", "Device status updated.");
        return "redirect:/settings";
    }

    @PostMapping("/devices/brightness")
    public String updateBrightness(@RequestParam Long deviceId,
                                   @RequestParam Integer brightness,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        if (isNotLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in before changing devices.");
            return "redirect:/login";
        }

        deviceService.updateDeviceBrightness(deviceId, brightness);
        redirectAttributes.addFlashAttribute("successMessage", "Brightness updated.");
        return "redirect:/lighting";
    }

    @PostMapping("/devices/mode")
    public String updateMode(@RequestParam Long deviceId,
                             @RequestParam String mode,
                             @RequestParam(defaultValue = "/settings") String returnTo,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        if (isNotLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in before changing devices.");
            return "redirect:/login";
        }

        if (mode == null || mode.isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mode is required.");
            return "redirect:" + returnTo;
        }

        deviceService.updateDeviceMode(deviceId, mode.trim().toLowerCase());
        redirectAttributes.addFlashAttribute("successMessage", "Device mode updated.");
        return "redirect:" + returnTo;
    }

    @PostMapping("/devices/delete")
    public String deleteDevice(@RequestParam Long deviceId,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (isNotLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in before deleting devices.");
            return "redirect:/login";
        }

        deviceService.deleteDevice(deviceId);
        redirectAttributes.addFlashAttribute("successMessage", "Device deleted successfully.");
        return "redirect:/settings";
    }

}