package com.app.service.pdfcreators;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.Presentation;
import com.app.objects.Template;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PresentationPdfCreator implements PdfCreator {

    @Override
    public void populateContent(PDPageContentStream c, Template template) {
        Presentation bannerTemplate = (Presentation) template;

        try {
            c.showText("Название презентации: " + bannerTemplate.getName());
            c.newLine();
            c.showText("Формат презентации: " + bannerTemplate.getFormat());
            c.newLine();
        } catch (IOException e) {
            throw new TemplateProcessingException("Error occurred while populating PDF content. Cause: " + e.getMessage());
        }
    }
}