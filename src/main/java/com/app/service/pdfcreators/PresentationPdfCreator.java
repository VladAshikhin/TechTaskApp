package com.app.service.pdfcreators;

import com.app.objects.Banner;
import com.app.objects.Presentation;
import com.app.objects.Template;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PresentationPdfCreator implements PdfCreator {

    @Override
    public void createPdf(Template template) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream content = new PDPageContentStream(document, page);
            content.setFont(PDType1Font.COURIER, 14);
            content.beginText();
            content.newLineAtOffset(25, 701);
            content.setLeading(14.5f);

            content.showText("Ваше техническое задание");
            content.newLine();

            populateContent(content, template);

            content.endText();
            content.close();

            document.save("tech_task.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void populateContent(PDPageContentStream c, Template template) {
        Presentation bannerTemplate = (Presentation) template;

        try {
            c.showText("Название презентации: " + bannerTemplate.getName());
            c.newLine();
            c.showText("Формат презентации: " + bannerTemplate.getFormat());
            c.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}