package com.app.service.pdfcreators;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.Template;
import com.app.objects.enums.TemplateType;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public interface PdfCreator {

    void populateContent(PDPageContentStream content, Template template);

    default String createPdf(Template template, TemplateType type) {
        String fontPath = "Inter-Light.ttf";
        String targetDir = "generated_pdf/";
        String fileName = targetDir.concat(type.getValue().concat("_tech_task.pdf"));

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream content = new PDPageContentStream(document, page);
            PDFont pdFont = PDType0Font.load(document, new File(fontPath));
            content.setFont(pdFont, 14);
            content.beginText();
            content.newLineAtOffset(25, 701);
            content.setLeading(14.5f);

            content.showText("Ваше техническое задание");
            content.newLine();

            populateContent(content, template);

            content.endText();
            content.close();

            document.save(fileName);
            document.close();
        } catch (IOException e) {
            throw new TemplateProcessingException("Error occurred while creating PDF. Cause: " + e.getMessage());
        }
        return fileName;
    }
}