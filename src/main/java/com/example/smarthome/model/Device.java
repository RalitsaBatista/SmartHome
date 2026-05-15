package com.example.smarthome.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
public class Device {
    private LocalDateTime lastUpdated;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;       // e.g. "Living Room Light"

    private String type;       // e.g. "light", "thermostat", "lock", "camera"

    private String status;     // e.g. "on" / "off"

    private String location;   // e.g. "Living Room", "Kitchen", "Bedroom"

    private String room;       // e.g. "Living Room", "Kitchen", "Bedroom"

    private Integer brightness; // e.g. 0-100 for lights

    private String mode; // e.g. "auto", "heat", "cool", "eco", "armed", "disarmed"

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
        this.room = location;
        this.brightness = 100;
        this.mode = "default";
        this.lastUpdated = LocalDateTime.now();
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
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
        return "Device{id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", room='" + room + '\'' +
                ", brightness=" + brightness +
                ", mode='" + mode + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}