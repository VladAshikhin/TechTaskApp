package com.app.service;

import com.app.objects.Task;
import org.springframework.stereotype.Component;

@Component
public class PerformTaskService {

    public void performPdf(Task task) {
        System.out.println("Service result: " + task.toString());
        //System.out.println(task.getCompany());
        //System.out.println(task.getDescription());
    /*for(Object o : data.values()) {
        System.out.println(o);
    }*/

    }
}