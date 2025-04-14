package com.vodafone.device.service;

import com.vodafone.device.exception.CustomEntityNotFoundException;
import com.vodafone.device.model.dao.DeviceRepository;
import com.vodafone.device.model.entity.Device;
import com.vodafone.device.utils.TestDataGen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class DeviceServiceTest {

    @TestConfiguration
    static class DeviceServiceImplTestContextConfiguration {
        @Bean
        public DeviceService deviceService() {
            return new DeviceServiceImpl();
        }
    }

    @MockBean
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceService deviceService;

    @Test
    public void validDeviceID_shouldReturnEntity() {
        final Long id = 1L;

        given(deviceRepository.findById(id)).willReturn(Optional.of(TestDataGen.getADevice()));

        final Device deviceRetrieved = deviceService.getDevice(1L);

        assertEquals(deviceRetrieved.getDeviceID(), TestDataGen.getADevice().getDeviceID());
    }

    @Test
    public void invalidDeviceID_shouldThrowException() {
        final Long id = 10L;

        given(deviceRepository.findById(id)).willReturn(Optional.empty());

        assertThrows(CustomEntityNotFoundException.class, () -> deviceService.getDevice(id));
    }
}
