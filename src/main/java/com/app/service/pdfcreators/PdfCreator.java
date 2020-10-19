package com.app.service.pdfcreators;

import com.app.objects.Template;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

@Service
public interface PdfCreator {

    void populateContent(PDPageContentStream content, Template template);

    void createPdf(Template template);

}