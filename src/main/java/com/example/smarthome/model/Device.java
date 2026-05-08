package com.example.smarthome.model;

import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;       // e.g. "Living Room Light"

    private String type;       // e.g. "light", "thermostat", "lock", "camera"

    private String status;     // e.g. "on" / "off"

    private String location;   // e.g. "Living Room", "Kitchen", "Bedroom"

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // --- Constructors ---

    public Device() {}

    public Device(String name, String type, String status, String location, User user) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
        this.user = user;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // --- Utility ---

    public boolean isOn() {
        return "on".equalsIgnoreCase(this.status);
    }

    @Override
    public String toString() {
        return "Device{id=" + id + ", name='" + name + "', type='" + type + "', status='" + status + "'}";
    }
}