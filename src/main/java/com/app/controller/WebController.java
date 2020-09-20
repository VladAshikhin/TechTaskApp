package com.app.controller;


import com.app.objects.Task;
import com.app.service.PerformTaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@Controller
//@org.springframework.web.bind.annotation.RestController
public class WebController {

    @Autowired
    PerformTaskService service;

    // Using @RestController without @ResponseBody is the same as
    // using @Controller with @ResponseBody

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String welcome() {
        return "index";
    }

    @PostMapping(value = "/pdf")
    public ResponseEntity<HttpStatus> preparePdf(@RequestBody String data) throws JsonProcessingException {
        System.out.println("Data input: " + data);
        ObjectMapper mapper = new ObjectMapper();
        Task task = mapper.readValue(data, Task.class);
        try {
            service.performPdf(task);
        } catch (Exception e) {
            System.out.println("Error in controller.");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        System.out.println("Success in controller.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}