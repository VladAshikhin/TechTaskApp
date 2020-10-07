package com.app.controller;


import com.app.objects.templatetypes.CorporateStyle;
import com.app.service.PerformPdfService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @Autowired
    PerformPdfService service;

    // Using @RestController without @ResponseBody is the same as
    // using @Controller with @ResponseBody

    @ResponseBody
    public String welcome() {
        return "index";
    }

    @PostMapping(value = "/pdf")
    public ResponseEntity<HttpStatus> preparePdf(@RequestBody String data) throws JsonProcessingException {
        System.out.println("Data input: " + data);
        ObjectMapper mapper = new ObjectMapper();
        CorporateStyle corporateStyle = mapper.readValue(data, CorporateStyle.class);
        try {
            service.performPdf(corporateStyle);
        } catch (Exception e) {
            System.out.println("Error in controller.");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        System.out.println("Success in controller.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}