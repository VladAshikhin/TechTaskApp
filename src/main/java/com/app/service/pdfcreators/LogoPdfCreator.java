package com.app.service.pdfcreators;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

@Service
public class LogoPdfCreator implements PdfCreator {
    @Override
    public void populateContent(PDPageContentStream c, Object template) {

    }

    @Override
    public void createPrf(Object template) {

    }
}
