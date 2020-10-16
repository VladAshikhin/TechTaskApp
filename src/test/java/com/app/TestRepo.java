package com.app;

import com.app.objects.CorporateStyle;
import com.app.objects.Logo;
import com.app.repository.CorporateStyleRepository;
import com.app.repository.LogoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testReturnNotEmptyTableWhenEntryAdded() {
        Logo entry = new Logo("LogoEntry", "100x200", ".jpd");
        logoRepository.saveAndFlush(entry);

        List<Logo> logos = logoRepository.findAll();

        Assert.assertNotNull(logos);
    }


}
