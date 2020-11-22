package com.app.service;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.*;
import com.app.objects.enums.TemplateType;
import com.app.repository.BannerRepository;
import com.app.repository.CorporateStyleRepository;
import com.app.repository.LogoRepository;
import com.app.repository.PresentationRepository;
import com.app.service.pdfcreators.PdfCreator;
import com.app.service.pdfcreators.PdfCreatorManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

@Service
public class TemplateService {

    @Autowired
    private LogoRepository logoRepository;
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private PresentationRepository presentationRepository;
    @Autowired
    private CorporateStyleRepository corporateStyleRepository;

    @Autowired
    PdfCreatorManager pdfCreatorManager;

    public ResponseEntity<String> createPdfFromTemplate(Map<String, Object> dataObject) throws IOException {

        if (!dataObject.containsKey("type") || !dataObject.containsKey("data")) {
            throw new TemplateProcessingException("Couldn't define Template Type or Data.");
        }

        TemplateType type = TemplateType.getType(dataObject.get("type"));

        Map<String, String> data = (Map<String, String>) dataObject.get("data");

        Template template = defineTypeAndSave(data, type);

        PdfCreator pdfCreator = pdfCreatorManager.definePdfCreator(template);

        String fileName = pdfCreator.createPdf(template, type);
        System.out.println("Filename: " + fileName);

        return prepareResponse(fileName);
    }

    public ResponseEntity<String> prepareResponse(String filePath) throws IOException {

        File pdf = new File(filePath);
        byte[] fileBytes = FileUtils.readFileToByteArray(pdf);

        System.out.println("File as ByteArray: " + Arrays.toString(fileBytes));

        ResponseEntity.BodyBuilder res =ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM);

                return res.body(Base64.getEncoder().encodeToString(fileBytes));
    }

    private Template defineTypeAndSave(Map<String, String> data, TemplateType type) {
        ObjectMapper mapper = new ObjectMapper();

        Template template;

        switch (type) {
            case LOGO:
                template = mapper.convertValue(data, Logo.class);
                logoRepository.saveAndFlush((Logo) template);
                break;
            case BANNER:
                template = mapper.convertValue(data, Banner.class);
                bannerRepository.saveAndFlush((Banner) template);
                break;
            case PRESENTATION:
                template = mapper.convertValue(data, Presentation.class);
                presentationRepository.saveAndFlush((Presentation) template);
                break;
            case CORPORATE_STYLE:
                template = mapper.convertValue(data, CorporateStyle.class);
                corporateStyleRepository.saveAndFlush((CorporateStyle) template);
                break;
            default:
                throw new TemplateProcessingException("Undefined template type " + type);
        }

        return template;
    }
}