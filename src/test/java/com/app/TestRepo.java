package com.app;

import com.app.objects.CorporateStyle;
import com.app.objects.Logo;
import com.app.repository.CorporateStyleRepository;
import com.app.repository.LogoRepository;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
public class TestRepo {

    @Autowired
    LogoRepository logoRepository;
    @Autowired
    CorporateStyleRepository styleRepo;

    @Test
    public void testRepo() {
        List<CorporateStyle> logos = styleRepo.findAll();

    }

    @Test
    public void testReturnNotEmptyTableWhenEntryAdded() throws IOException {
        File pdf = new File("tech_task_eng.pdf");
        byte[] fileBytes = FileUtils.readFileToByteArray(pdf);

        Logo entry = new Logo("LogoEntry", "100x200", ".jpd", fileBytes);

        logoRepository.saveAndFlush(entry);

        List<Logo> logos = logoRepository.findAll();

        Assert.assertNotNull(logos);
    }
}