package com.app.repository.impl;

import com.app.objects.CorporateStyle;
import com.app.objects.Template;
import com.app.objects.enums.TemplateType;
import com.app.repository.CorporateStyleRepository;
import com.app.service.pdfcreators.PdfCreator;
import com.app.service.pdfcreators.PdfCreatorManager;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class CorporateStyleService {

    @Autowired
    CorporateStyleRepository corporateStyleRepository;
    @Autowired
    public PdfCreatorManager pdfCreatorManager;

    public byte[] saveTemplate(Template template) {
        CorporateStyle logo = (CorporateStyle) template;
        TemplateType type = TemplateType.CORPORATE_STYLE;

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

        corporateStyleRepository.saveAndFlush(logo);

        return fileBytes;
    }
}