package com.app.service;

import com.app.objects.templatetypes.Banner;
import com.app.objects.templatetypes.CorporateStyle;
import com.app.objects.templatetypes.Logo;
import com.app.objects.templatetypes.Presentation;
import com.app.repo.BannerRepo;
import com.app.repo.LogoRepo;
import com.app.repo.PresentationRepo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PerformPdfService {

    @Autowired
    private LogoRepo logoRepo;
    @Autowired
    private BannerRepo bannerRepo;
    @Autowired
    private PresentationRepo presentationRepo;

    public void preProcess(CorporateStyle corporateStyle) {

        performPdf(corporateStyle);
    }

    private void performPdf(CorporateStyle corporateStyle) {

        List<Logo> logos = logoRepo.findAll();
        List<Banner> banners = bannerRepo.findAll();
        List<Presentation> presentations = presentationRepo.findAll();

        logos.forEach(logo -> System.out.println("Logo: " + logo.getId() + " " + logo.getName()));

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

    public void populateContent(PDPageContentStream c, CorporateStyle corporateStyle) throws IOException {

        String needButton = corporateStyle.getButton().equalsIgnoreCase("yes") ? "Ok, we need a button" : "We don't need a button";

        c.showText("Your company name: " + corporateStyle.getCompany());
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