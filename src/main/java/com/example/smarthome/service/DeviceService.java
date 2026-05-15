package com.example.smarthome.service;

import com.example.smarthome.model.Device;
import com.example.smarthome.model.User;
import com.example.smarthome.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getDeviceByUserAndType(Long userId, String type) {
        return deviceRepository.findByUserIdAndType(userId, type);
    }

    public void addDevice(String name, String type, User user) {
        addDevice(name, type, "Unassigned", user);
    }

    public void addDevice(String name, String type, String room, User user) {
        Device device = new Device();
        device.setName(name);
        device.setType(type);
        device.setStatus("off");
        device.setRoom(room);
        device.setLocation(room);
        device.setBrightness(defaultBrightnessForType(type));
        device.setMode(defaultModeForType(type));
        device.setLastUpdated(LocalDateTime.now());
        device.setUser(user);
        deviceRepository.save(device);
    }

    public void updateDeviceStatus(Long deviceId, String status) {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        device.setStatus(status);
        device.setLastUpdated(LocalDateTime.now());
        deviceRepository.save(device);
    }

    public void toggleDevice(Long deviceId) {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        device.setStatus("on".equalsIgnoreCase(device.getStatus()) ? "off" : "on");
        device.setLastUpdated(LocalDateTime.now());
        deviceRepository.save(device);
    }

    public void updateDeviceBrightness(Long deviceId, Integer brightness) {
        Device device = deviceRepository.findById(deviceId).orElseThrow();

        int safeBrightness = Math.max(0, Math.min(100, brightness));

        device.setBrightness(safeBrightness);
        device.setLastUpdated(LocalDateTime.now());
        deviceRepository.save(device);
    }

    public void updateDeviceMode(Long deviceId, String mode) {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        device.setMode(mode);
        device.setLastUpdated(LocalDateTime.now());
        deviceRepository.save(device);
    }

    public void updateDeviceRoom(Long deviceId, String room) {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        device.setRoom(room);
        device.setLocation(room);
        device.setLastUpdated(LocalDateTime.now());
        deviceRepository.save(device);
    }

    public void deleteDevice(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    private Integer defaultBrightnessForType(String type) {
        if ("light".equalsIgnoreCase(type)) {
            return 100;
        }

        return 0;
    }

    private String defaultModeForType(String type) {
        if ("thermostat".equalsIgnoreCase(type)) {
            return "auto";
        }

        if ("camera".equalsIgnoreCase(type)) {
            return "recording";
        }

        if ("lock".equalsIgnoreCase(type)) {
            return "locked";
        }

        if ("sensor".equalsIgnoreCase(type)) {
            return "monitoring";
        }

        return "default";
    }
}