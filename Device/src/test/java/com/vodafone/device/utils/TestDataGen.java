package com.vodafone.device.utils;

import com.vodafone.device.model.entity.Device;

import java.util.ArrayList;
import java.util.List;

public class TestDataGen {

    public static Device getADevice() {
        Device device = new Device();
        device.setDeviceID(1L);
        device.setBrand("Apple");
        device.setModel("iPhone 12 Pro");
        device.setPrice("1100");

        return device;
    }

    public static List<Device> getListOfDevices() {
        Device deviceOne = new Device();
        deviceOne.setDeviceID(1L);
        deviceOne.setBrand("Apple");
        deviceOne.setModel("iPhone 12 Pro");
        deviceOne.setPrice("1100");

        Device deviceTwo = new Device();
        deviceTwo.setDeviceID(1L);
        deviceTwo.setBrand("Samsung");
        deviceTwo.setModel("Galaxy S20");
        deviceTwo.setPrice("1000");

        List<Device> deviceList = new ArrayList<>();
        deviceList.add(deviceOne);
        deviceList.add(deviceTwo);

        return deviceList;
    }
}
