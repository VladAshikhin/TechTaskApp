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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void processTemplate(Map<String, Object> dataObject) {
        if (!dataObject.containsKey("type") || !dataObject.containsKey("data")) {
            throw new TemplateProcessingException("Couldn't define Template Type or Data.");
        }

        TemplateType type = TemplateType.getType(dataObject.get("type"));

        Map<String, String> data = (Map<String, String>) dataObject.get("data");

        Template template = defineTypeAndSave(data, type);

        PdfCreator pdfCreator = pdfCreatorManager.definePdfCreator(template);

        pdfCreator.createPdf(template);
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