package com.vodafone.device.service;

import com.vodafone.device.exception.CustomEntityNotFoundException;
import com.vodafone.device.model.dao.DeviceRepository;
import com.vodafone.device.model.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device getDevice(Long deviceID) {
        return deviceRepository.findById(deviceID)
                .orElseThrow(() -> new CustomEntityNotFoundException("No such device found with ID: " + deviceID));
    }

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }
}
