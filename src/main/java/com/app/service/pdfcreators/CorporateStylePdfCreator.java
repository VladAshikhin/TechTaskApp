package com.app.service.pdfcreators;

import com.app.objects.CorporateStyle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CorporateStylePdfCreator implements PdfCreator {

    @Override
    public void createPrf(Object template) {
        CorporateStyle corporateStyle = (CorporateStyle) template;

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

            populateContent(content, corporateStyle);

            content.endText();
            content.close();

            document.save("tech_task.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void populateContent(PDPageContentStream c, Object template) {

        CorporateStyle corporateStyle = (CorporateStyle) template;

        String needButton = corporateStyle.getButtonRequired() ? "Ok, we need a button" : "We don't need a button";

        try {
            c.showText("Your company name: " + corporateStyle.getCompanyName());
            c.newLine();
            c.showText("Your maket size: " + corporateStyle.getMaketSize());
            c.newLine();
            c.showText("Orientation of your maket: " + corporateStyle.getMaketOrientation());
            c.newLine();
            c.showText("Info: " + corporateStyle.getInfo());
            c.newLine();
            c.showText("Platform: " + corporateStyle.getPlatform());
            c.newLine();
            c.showText("Deadline is: " + corporateStyle.getDeadline());
            c.newLine();
            c.showText(needButton);
            c.newLine();
            c.showText("Button Text: " + corporateStyle.getButtonText());
            c.newLine();
            c.showText("Primary text: " + corporateStyle.getPrimaryMaketText());
            c.newLine();
            c.showText("Secondary text: " + corporateStyle.getSecondaryMaketText());
            c.newLine();
            c.showText("Contacts: " + corporateStyle.getContacts());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
