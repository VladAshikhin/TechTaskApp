package com.app.service.pdfcreators;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.enums.TemplateType;
import org.springframework.stereotype.Service;

@Service
public class PdfCreatorManager {

    public PdfCreator definePdfCreator(TemplateType type) {

        switch (type) {
            case LOGO:
                return new LogoPdfCreator();
            case BANNER:
                return new BannerPfdCreator();
            case PRESENTATION:
                return new PresentationPdfCreator();
            case CORPORATE_STYLE:
                return new CorporateStylePdfCreator();
            default:
                throw new TemplateProcessingException("Undefined class.");
        }
    }
}