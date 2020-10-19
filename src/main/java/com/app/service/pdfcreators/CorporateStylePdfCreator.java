package com.app.service.pdfcreators;

import com.app.objects.CorporateStyle;
import com.app.objects.Template;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CorporateStylePdfCreator implements PdfCreator {

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

        CorporateStyle corporateStyle = (CorporateStyle) template;

        String needButton = corporateStyle.getButtonRequired() ? "Кнопка необходима" : "Кнопка не нужна";

        try {
            c.showText("Название компании: " + corporateStyle.getCompanyName());
            c.newLine();
            c.showText("Размер макета: " + corporateStyle.getMaketSize());
            c.newLine();
            c.showText("Ориентация макета: " + corporateStyle.getMaketOrientation());
            c.newLine();
            c.showText("Информация: " + corporateStyle.getInfo());
            c.newLine();
            c.showText("Платформа: " + corporateStyle.getPlatform());
            c.newLine();
            c.showText("Дедлайн: " + corporateStyle.getDeadline());
            c.newLine();
            c.showText(needButton);
            c.newLine();
            c.showText("Надпись на кнопке: " + corporateStyle.getButtonText());
            c.newLine();
            c.showText("Главный текст на макете: " + corporateStyle.getPrimaryMaketText());
            c.newLine();
            c.showText("Второстепенный текст на макете: " + corporateStyle.getSecondaryMaketText());
            c.newLine();
            c.showText("Контакты: " + corporateStyle.getContacts());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
