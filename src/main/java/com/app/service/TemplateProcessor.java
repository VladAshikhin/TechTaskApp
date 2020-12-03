package com.app.service;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.*;
import com.app.objects.enums.TemplateType;
import com.app.repository.BannerRepository;
import com.app.repository.CorporateStyleRepository;
import com.app.repository.PresentationRepository;
import com.app.service.templateservices.BannerService;
import com.app.service.templateservices.CorporateStyleService;
import com.app.service.templateservices.LogoService;
import com.app.service.templateservices.PresentationService;
import com.app.service.pdfcreators.PdfCreatorManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

@Service
public class TemplateProcessor {

    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private PresentationRepository presentationRepository;
    @Autowired
    private CorporateStyleRepository corporateStyleRepository;

    @Autowired
    private LogoService logoService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private PresentationService presentationService;
    @Autowired
    private CorporateStyleService corporateStyleService;

    @Autowired
    PdfCreatorManager pdfCreatorManager;

    public ResponseEntity<String> processTemplate(Map<String, Object> dataObject) throws IOException {

        if (!dataObject.containsKey("type") || !dataObject.containsKey("data")) {
            throw new TemplateProcessingException("Couldn't define Template Type or Data.");
        }

        TemplateType type = TemplateType.getType(dataObject.get("type"));
        Map<String, String> data = (Map<String, String>) dataObject.get("data");

        byte[] fileBytes = generatePdfAndSaveTemplate(data, type);
        return prepareResponse(fileBytes);
    }

    private byte[] generatePdfAndSaveTemplate(Map<String, String> data, TemplateType type) {
        ObjectMapper mapper = new ObjectMapper();

        Template template;
        byte[] fileBytes;

        switch (type) {
            case LOGO:
                template = mapper.convertValue(data, Logo.class);
                fileBytes = logoService.saveTemplate(template);
                break;
            case BANNER:
                template = mapper.convertValue(data, Banner.class);
                fileBytes = bannerService.saveTemplate(template);
                break;
            case PRESENTATION:
                template = mapper.convertValue(data, Presentation.class);
                fileBytes = presentationService.saveTemplate(template);
                break;
            case CORPORATE_STYLE:
                template = mapper.convertValue(data, CorporateStyle.class);
                fileBytes = corporateStyleService.saveTemplate(template);
                break;
            default:
                throw new TemplateProcessingException("Undefined template type " + type);
        }
        return fileBytes;
    }

    public ResponseEntity<String> prepareResponse(byte[] fileBytes) {

        System.out.println("File as ByteArray: " + Arrays.toString(fileBytes));

        ResponseEntity.BodyBuilder res = ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM);

        return res.body(Base64.getEncoder().encodeToString(fileBytes));
    }
}