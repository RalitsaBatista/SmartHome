package com.example.smarthome.service;

import com.example.smarthome.model.Device;
import com.example.smarthome.model.User;
import com.example.smarthome.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
   @Autowired
   private DeviceRepository deviceRepository;

    public List<Device> getDeviceByUserAndType(Long userId, String type) {
        return deviceRepository.findByUserIdAndType(userId, type);
    }

   public void addDevice(String name, String type, User user) {
       Device device = new Device();
       device.setName(name);
       device.setType(type);
       device.setStatus("off");
       device.setUser(user);
       deviceRepository.save(device);
   }

    public void updateDeviceStatus(Long deviceId, String status) {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        device.setStatus(status);
        deviceRepository.save(device);
    }

    public void toggleDevice(Long deviceId) {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        device.setStatus("on".equalsIgnoreCase(device.getStatus()) ? "off" : "on");
        deviceRepository.save(device);
    }

    public void deleteDevice(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }
}