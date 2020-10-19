package com.app.service.pdfcreators;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.*;
import org.springframework.stereotype.Service;

@Service
public class PdfCreatorManager {

    public PdfCreator definePdfCreator(Template template) {

        if (template instanceof Logo) {
            return new LogoPdfCreator();
        } else if (template instanceof Banner) {
            return new BannerPfdCreator();
        } else if (template instanceof Presentation) {
            return new PresentationPdfCreator();
        } else if (template instanceof CorporateStyle) {
            return new CorporateStylePdfCreator();
        } else {
            throw new TemplateProcessingException("Undefined class.");
        }
    }
}