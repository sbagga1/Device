package com.vodafone.device.controller;

import com.vodafone.device.exception.CustomEntityNotFoundException;
import com.vodafone.device.exception.CustomInternalServerError;
import com.vodafone.device.model.entity.Device;
import com.vodafone.device.service.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final DeviceServiceImpl deviceService;

    @Autowired
    public Controller(DeviceServiceImpl deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<?> getDeviceByID(@PathVariable Long id) {
        Device device;
        try {
            device = deviceService.getDevice(id);
        } catch (CustomEntityNotFoundException e) {
            throw new CustomEntityNotFoundException(e.getMessage());
        }

        return ResponseEntity.ok().body(device);
    }

    @PostMapping("/devices")
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        try {
            device = deviceService.addDevice(device);
        } catch (Exception e) {
            throw new CustomInternalServerError("Something went wrong while adding new device. Check payload.");
        }

        return ResponseEntity.ok().body(device);
    }
}
