package com.app.service;

import com.app.objects.Banner;
import com.app.objects.CorporateStyle;
import com.app.objects.Logo;
import com.app.objects.Presentation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TemplateService {

    @Autowired
    PerformPdfService pdfService;

    public void processTemplate(Map<String, Object> dataObject, ObjectMapper mapper) {
        String type = "";
        if (dataObject.containsKey("type")) {
            type = String.valueOf(dataObject.get("type"));
        }

        Map<String, String> data = (Map<String, String>) dataObject.get("data");

        Object template;

        switch (type) {
            case "logo":
                template = mapper.convertValue(data, Logo.class);
                break;
            case "banner":
                template = mapper.convertValue(data, Banner.class);
                break;
            case "presentation":
                template = mapper.convertValue(data, Presentation.class);
                break;
            case "corporateStyle":
                template = mapper.convertValue(data, CorporateStyle.class);
                break;
            default:
                throw new RuntimeException("Undefined template type " + type);
        }

        pdfService.preProcessTemplate(template);

    }
}