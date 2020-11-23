package com.app.repository.impl;

import com.app.objects.Logo;
import com.app.objects.Template;
import com.app.objects.enums.TemplateType;
import com.app.repository.LogoRepository;
import com.app.service.pdfcreators.PdfCreator;
import com.app.service.pdfcreators.PdfCreatorManager;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class LogoService {

    @Autowired
    public LogoRepository logoRepository;
    @Autowired
    public PdfCreatorManager pdfCreatorManager;

    public byte[] saveTemplate(Template template) {
        Logo logo = (Logo) template;
        TemplateType type = TemplateType.LOGO;

        PdfCreator pdfCreator = pdfCreatorManager.definePdfCreator(type);
        String filePath = pdfCreator.createPdf(logo, type);

        File pdf = new File(filePath);
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = FileUtils.readFileToByteArray(pdf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logo.setFileBytes(fileBytes);

        logoRepository.saveAndFlush(logo);

        return fileBytes;
    }
}