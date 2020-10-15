import com.app.Application;
import com.app.objects.templatetypes.Logo;
import com.app.repo.LogoRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RepoTest {

    @Autowired
    LogoRepo logoRepo;

    @Test
    public void testLogoRepo() {
        List<Logo> logos = logoRepo.findAll();

    }


}
