package com.example.smarthome.repository;

import com.example.smarthome.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByUserIdAndType(Long userId, String type);

    List<Device> findByUserId(Long userId);

    List<Device> findByStatus(String status);
}
/*
@Repository 
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUserIdAndType(Long userId, String type);
    List<Device> updateDeviceStatus(Long deviceId, String status);
}
*/
