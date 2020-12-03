package com.app.service.templateservices;

import com.app.exceptions.TemplateProcessingException;
import com.app.objects.Presentation;
import com.app.objects.Template;
import com.app.objects.enums.TemplateType;
import com.app.repository.PresentationRepository;
import com.app.service.pdfcreators.PdfCreator;
import com.app.service.pdfcreators.PdfCreatorManager;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PresentationService {

    @Autowired
    PresentationRepository presentationRepository;
    @Autowired
    PdfCreatorManager pdfCreatorManager;

    public byte[] saveTemplate(Template template) {
        Presentation presentation = (Presentation) template;
        TemplateType type = TemplateType.PRESENTATION;

        PdfCreator pdfCreator = pdfCreatorManager.definePdfCreator(type);
        String filePath = pdfCreator.createPdf(presentation, type);

        File pdf = new File(filePath);
        byte[] fileBytes;
        try {
            fileBytes = FileUtils.readFileToByteArray(pdf);
        } catch (IOException e) {
            throw new TemplateProcessingException("Failed to convert file to byte array. Type " + type);
        }

        presentation.setFileBytes(fileBytes);

        presentationRepository.saveAndFlush(presentation);

        return fileBytes;
    }
}