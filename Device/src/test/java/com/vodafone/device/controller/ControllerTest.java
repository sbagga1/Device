package com.vodafone.device.controller;

import com.vodafone.device.DeviceApiApplication;
import com.vodafone.device.model.entity.Device;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeviceApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void validDeviceID_shouldReturnWith200() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/devices/1"), HttpMethod.GET, entity, String.class);

        String expected = "{\"deviceID\":1,\"brand\":\"Apple\",\"model\":\"iPhone 12 Pro\",\"price\":\"1200\"}";

        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void invalidDeviceID_shouldFailWith404() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/devices/10"), HttpMethod.GET, entity, String.class);

        String expected = "{\"status\":404,\"message\":\"No such device found with ID: 10\",\"success\":false}";

        assertEquals(expected, response.getBody());
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void invalidDeviceIDFormat_shouldFailWith400() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/devices/as"), HttpMethod.GET, entity, String.class);

        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    public void validPOST_shouldSucceed() {
        Device device = new Device();
        device.setBrand("Samsung");
        device.setModel("Galaxy 99");
        device.setPrice("999");

        HttpEntity<Device> entity = new HttpEntity<>(device, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/devices"), HttpMethod.POST, entity, Device.class);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    private URI createURLWithPort(String uri) {
        return URI.create("http://localhost:" + port + uri);
    }
}
