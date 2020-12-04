package com.app.service.pdfcreators;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.Logo;
import com.app.objects.Template;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LogoPdfCreator implements PdfCreator {

    @Override
    public void populateContent(PDPageContentStream c, Template template) {
        Logo logoTemplate = (Logo) template;

        try {
            c.showText("Название логотипа: " + logoTemplate.getName());
            c.newLine();
           // c.showText("Размер логотипа: " + logoTemplate.getSize());
            c.newLine();
            //c.showText("Формат логотипа: " + logoTemplate.getFormat());
            c.newLine();
        } catch (IOException e) {
            throw new TemplateProcessingException("Error occurred while populating PDF content. Cause: " + e.getMessage());
        }
    }
}