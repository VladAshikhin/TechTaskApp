package com.app.service;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.Banner;
import com.app.objects.CorporateStyle;
import com.app.objects.Logo;
import com.app.objects.Presentation;
import com.app.objects.enums.TemplateType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TemplateService {

    @Autowired
    PerformPdfService pdfService;

    public void processTemplate(Map<String, Object> dataObject, ObjectMapper mapper) {
        if (!dataObject.containsKey("type") || !dataObject.containsKey("data")) {
            throw new TemplateProcessingException("Couldn't define Template Type or Data.");
        }

        TemplateType type = TemplateType.getType(dataObject.get("type"));

        Map<String, String> data = (Map<String, String>) dataObject.get("data");

        Object template;

        switch (type) {
            case LOGO:
                template = mapper.convertValue(data, Logo.class);
                break;
            case BANNER:
                template = mapper.convertValue(data, Banner.class);
                break;
            case PRESENTATION:
                template = mapper.convertValue(data, Presentation.class);
                break;
            case CORPORATE_STYLE:
                template = mapper.convertValue(data, CorporateStyle.class);
                break;
            default:
                throw new TemplateProcessingException("Undefined template type " + type);
        }

        pdfService.preProcessTemplate(template);
    }
}