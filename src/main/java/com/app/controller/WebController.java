package com.app.controller;


import com.app.service.TemplateProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    TemplateProcessor service;

    @ResponseBody
    public String welcome() {
        return "index";
    }

    @PostMapping(value = "/pdf/create")
    public ResponseEntity<?> preparePdf(@RequestBody String data) throws IOException {
        System.out.println("Data input: " + data);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> dataObject = mapper.readValue(data, Map.class);

        try {
            return service.processTemplate(dataObject);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.toString());
        }
    }
}