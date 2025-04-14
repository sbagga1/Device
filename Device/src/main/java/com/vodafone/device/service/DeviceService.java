package com.vodafone.device.service;

import com.vodafone.device.model.entity.Device;

public interface DeviceService {
    Device getDevice(Long deviceID);
    Device addDevice(Device device);
}