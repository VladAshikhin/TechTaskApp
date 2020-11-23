package com.app.repository.impl;

import com.app.objects.Banner;
import com.app.objects.Template;
import com.app.objects.enums.TemplateType;
import com.app.repository.BannerRepository;
import com.app.service.pdfcreators.PdfCreator;
import com.app.service.pdfcreators.PdfCreatorManager;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class BannerService {

    @Autowired
    public BannerRepository bannerRepository;
    @Autowired
    public PdfCreatorManager pdfCreatorManager;

    public byte[] saveTemplate(Template template) {
        Banner banner = (Banner) template;
        TemplateType type = TemplateType.BANNER;

        PdfCreator pdfCreator = pdfCreatorManager.definePdfCreator(type);
        String filePath = pdfCreator.createPdf(banner, type);

        File pdf = new File(filePath);
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = FileUtils.readFileToByteArray(pdf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        banner.setFileBytes(fileBytes);

        bannerRepository.saveAndFlush(banner);

        return fileBytes;
    }
}