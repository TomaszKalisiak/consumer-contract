package com.maersk.poc.consumercontract.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PocController {

    private final RestTemplate restTemplate;

    @Autowired
    public PocController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/calculate")
    public String checkOddOrEven(@RequestParam("number") Integer number) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        ResponseEntity<String> responseEntity = restTemplate
                .exchange("http://localhost:8090/validate/prime-number?number=" + number,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class);
        return responseEntity.getBody();
    }
}
