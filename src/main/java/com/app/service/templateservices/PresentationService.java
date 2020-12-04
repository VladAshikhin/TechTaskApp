package com.app.service.templateservices;

import com.app.objects.Presentation;
import com.app.objects.Template;
import com.app.objects.enums.TemplateType;
import com.app.repository.PresentationRepository;
import com.app.service.pdfcreators.PdfCreator;
import com.app.service.pdfcreators.PdfCreatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresentationService {

    @Autowired
    PresentationRepository presentationRepository;
    @Autowired
    PdfCreatorManager pdfCreatorManager;

    public byte[] saveTemplate(Template template) {
        Presentation presentation = (Presentation) template;

        PdfCreator pdfCreator = pdfCreatorManager.definePdfCreator(TemplateType.PRESENTATION);
        byte[] byteArray = pdfCreator.createPdf(presentation);

        presentation.setFileBytes(byteArray);
        presentationRepository.saveAndFlush(presentation);

        return byteArray;
    }
}