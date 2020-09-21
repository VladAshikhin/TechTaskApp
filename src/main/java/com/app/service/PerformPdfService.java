package com.app.service;

import com.app.objects.Task;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PerformPdfService {

    public void performPdf(Task task) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream content = new PDPageContentStream(document, page);
            content.setFont(PDType1Font.COURIER, 14);
            content.beginText();
            content.newLineAtOffset(25, 701);
            content.setLeading(14.5f);

            content.showText("Your tech task");
            content.newLine();

            populateContent(content, task);

            content.endText();
            content.close();

            document.save("tech_task.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateContent(PDPageContentStream content, Task task) throws IOException {

        content.showText("Your company name: " + task.getCompany());
        content.newLine();
        content.showText("Your maket size: " + task.getMaketSize());
        content.newLine();
        content.showText("Info: " + task.getInfo());
        content.newLine();
        content.showText("Platform: " + task.getPlatform());
        content.newLine();
        content.showText("Button Text: " + task.getButtonText());
        content.newLine();
        content.showText("Primary text: " + task.getPrimaryMaketText());
        content.newLine();
        content.showText("Secondary text: " + task.getSecondaryMaketText());
        content.newLine();
        content.showText("Contacts: " + task.getContacts());
    }
}