/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms;

import javax.annotation.Resource;
import static org.junit.Assert.assertNotNull;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.org.rbc1b.roms.security.RomsPermissionEvaluator;

/**
 *
 * @author oliver.elder.esq
 */
@ContextConfiguration({
    "/WEB-INF/spring/applicationContext.xml",
    "/WEB-INF/spring/applicationContext-security.xml",
    "/WEB-INF/spring/dispatcher-servlet.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringIntegrationTest {

    @Resource(name = "romsPermissionEvaluator")
    private RomsPermissionEvaluator configuration;

    /**
     */
    @Test
    @Ignore
    public void testSpringConfiguration() {
        assertNotNull(configuration);
    }
}
