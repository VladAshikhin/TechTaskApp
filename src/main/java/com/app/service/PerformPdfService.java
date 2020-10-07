package com.app.service;

import com.app.objects.templatetypes.Task;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class PerformPdfService {

    public void performPdf(Task task) {
        System.out.println("Sources are: " + Arrays.asList(task.getSource()));

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

    public void populateContent(PDPageContentStream c, Task task) throws IOException {

        String needButton = task.getButton().equalsIgnoreCase("yes") ? "Ok, we need a button" : "We don't need a button";

        c.showText("Your company name: " + task.getCompany());
        c.newLine();
        c.showText("Your maket size: " + task.getMaketSize());
        c.newLine();
        c.showText("Orientation of your maket: " + task.getMaketOrientation());
        c.newLine();
        c.showText("Info: " + task.getInfo());
        c.newLine();
        c.showText("Platform: " + task.getPlatform());
        c.newLine();
        c.showText("Deadline is: " + task.getDeadline());
        c.newLine();
        c.showText(needButton);
        c.newLine();
        c.showText("Button Text: " + task.getButtonText());
        c.newLine();
        c.showText("Primary text: " + task.getPrimaryMaketText());
        c.newLine();
        c.showText("Secondary text: " + task.getSecondaryMaketText());
        c.newLine();
        c.showText("Contacts: " + task.getContacts());
    }
}