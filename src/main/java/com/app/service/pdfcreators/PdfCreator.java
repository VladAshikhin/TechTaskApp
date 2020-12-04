package com.app.service.pdfcreators;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.Template;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public interface PdfCreator {

    void populateContent(PDPageContentStream content, Template template);

    default byte[] createPdf(Template template) {
        String fontPath = "Inter-Light.ttf";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

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


            document.save(baos);
            document.close();
        } catch (IOException e) {
            throw new TemplateProcessingException("Error occurred while creating PDF. Cause: " + e.getMessage());
        }
        return baos.toByteArray();
    }
}