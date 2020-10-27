package com.app.service.pdfcreators;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.Banner;
import com.app.objects.Template;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BannerPfdCreator implements PdfCreator {

    @Override
    public void populateContent(PDPageContentStream c, Template template) {
        Banner bannerTemplate = (Banner) template;

        try {
            c.showText("Название баннера: " + bannerTemplate.getName());
            c.newLine();
            c.showText("Размер баннера: " + bannerTemplate.getSize());
            c.newLine();
            c.showText("Формат баннера: " + bannerTemplate.getFormat());
            c.newLine();
        } catch (IOException e) {
            throw new TemplateProcessingException("Error occurred while populating PDF content. Cause: " + e.getMessage());
        }
    }
}