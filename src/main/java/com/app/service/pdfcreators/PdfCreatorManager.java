package com.app.service.pdfcreators;

import com.app.exceptions.TemplateProcessingException;
import org.springframework.stereotype.Service;

@Service
public class PdfCreatorManager {

    public PdfCreator definePdfCreator(Object template) {

        String className = template.getClass().getSimpleName();

        switch (className) {
            case "Logo":
                return new LogoPdfCreator();
            case "Banner":
                return new BannerPfdCreator();
            case "Presentation":
                return new PresentationPdfCreator();
            case "CorporateStyle":
                return new CorporateStylePdfCreator();
            default:
                throw new TemplateProcessingException("Undefined class " + className);
        }

    }

}
