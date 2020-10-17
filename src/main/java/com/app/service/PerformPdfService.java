package com.app.service;

import com.app.objects.Banner;
import com.app.objects.CorporateStyle;
import com.app.objects.Logo;
import com.app.objects.Presentation;
import com.app.repository.BannerRepository;
import com.app.repository.CorporateStyleRepository;
import com.app.repository.LogoRepository;
import com.app.repository.PresentationRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PerformPdfService {

    @Autowired
    private LogoRepository logoRepository;
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private PresentationRepository presentationRepository;
    @Autowired
    private CorporateStyleRepository corporateStyleRepository;

    public void preProcessTemplate(Object template) {
        String className = template.getClass().getSimpleName();

        switch (className) {
            case "Logo":
                Logo logo = (Logo) template;
                logoRepository.saveAndFlush(logo);
                break;
            case "Banner":
                Banner banner = (Banner) template;
                bannerRepository.saveAndFlush(banner);
                break;
            case "Presentation":
                Presentation presentation = (Presentation) template;
                presentationRepository.saveAndFlush(presentation);
                break;
            case "CorporateStyle":
                CorporateStyle style = (CorporateStyle) template;
                corporateStyleRepository.saveAndFlush(style);
                break;
            default:
                throw new RuntimeException("Undefined class " + className);
        }

    }

    private void performPdf(CorporateStyle corporateStyle) {

        System.out.println("Dedaline: " + corporateStyle.getDeadline());

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

    private void populateContent(PDPageContentStream c, CorporateStyle corporateStyle) throws IOException {

        String needButton = corporateStyle.getButtonRequired() ? "Ok, we need a button" : "We don't need a button";

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
    }
}