package com.app.service.templateservices;

import com.app.objects.CorporateStyle;
import com.app.objects.Template;
import com.app.objects.enums.TemplateType;
import com.app.repository.CorporateStyleRepository;
import com.app.service.pdfcreators.PdfCreator;
import com.app.service.pdfcreators.PdfCreatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorporateStyleService {

    @Autowired
    CorporateStyleRepository corporateStyleRepository;
    @Autowired
    public PdfCreatorManager pdfCreatorManager;

    public byte[] saveTemplate(Template template) {
        CorporateStyle logo = (CorporateStyle) template;

        PdfCreator pdfCreator = pdfCreatorManager.definePdfCreator(TemplateType.CORPORATE_STYLE);
        byte[] byteArray = pdfCreator.createPdf(logo);

        logo.setFileBytes(byteArray);
        corporateStyleRepository.saveAndFlush(logo);

        return byteArray;
    }
}