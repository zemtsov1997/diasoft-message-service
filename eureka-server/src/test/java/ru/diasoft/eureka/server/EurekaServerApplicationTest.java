package ru.diasoft.eureka.server;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testing eureka-server")
public class EurekaServerApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("checking the health status")
    public void checkingHealthStatus() throws Exception {
        URL urlHealth = new URL("http://localhost:"+port+"/actuator/health");

        JSONObject jsonHealth = new JSONObject();
        jsonHealth.put("status", "UP");

        String htmlHealth = this.restTemplate.getForObject(urlHealth.toURI(), String.class);

        JSONAssert.assertEquals(htmlHealth, jsonHealth, true);
    }

}

