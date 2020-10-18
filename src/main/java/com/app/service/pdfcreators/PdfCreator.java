package com.app.service.pdfcreators;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

@Service
public interface PdfCreator {

    void populateContent(PDPageContentStream content, Object template);

    void createPdf(Object template);

}