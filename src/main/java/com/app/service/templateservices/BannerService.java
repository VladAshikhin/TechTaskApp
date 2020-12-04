package com.app.service.templateservices;

import com.app.objects.Banner;
import com.app.objects.Template;
import com.app.objects.enums.TemplateType;
import com.app.repository.BannerRepository;
import com.app.service.pdfcreators.PdfCreator;
import com.app.service.pdfcreators.PdfCreatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {

    @Autowired
    public BannerRepository bannerRepository;
    @Autowired
    public PdfCreatorManager pdfCreatorManager;

    public byte[] saveTemplate(Template template) {
        Banner banner = (Banner) template;

        PdfCreator pdfCreator = pdfCreatorManager.definePdfCreator(TemplateType.BANNER);
        byte[] byteArray = pdfCreator.createPdf(banner);

        banner.setFileBytes(byteArray);
        bannerRepository.saveAndFlush(banner);

        return byteArray;
    }
}