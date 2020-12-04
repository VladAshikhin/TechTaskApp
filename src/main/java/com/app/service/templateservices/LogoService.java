package com.app.service.templateservices;

import com.app.objects.Logo;
import com.app.objects.Template;
import com.app.objects.enums.TemplateType;
import com.app.repository.LogoRepository;
import com.app.service.pdfcreators.PdfCreator;
import com.app.service.pdfcreators.PdfCreatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoService {

    @Autowired
    public LogoRepository logoRepository;
    @Autowired
    public PdfCreatorManager pdfCreatorManager;

    public byte[] saveTemplate(Template template) {
        Logo logo = (Logo) template;

        PdfCreator pdfCreator = pdfCreatorManager.definePdfCreator(TemplateType.LOGO);
        byte[] byteArray = pdfCreator.createPdf(logo);

        logo.setFileBytes(byteArray);
        logoRepository.saveAndFlush(logo);

        return byteArray;
    }
}