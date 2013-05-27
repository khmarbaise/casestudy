package com.soebes.casestudy;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.ZutatBO;
import com.soebes.casestudy.dao.DAOFactory;
import com.soebes.casestudy.dao.IdDAO;

public class ZutatBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(ZutatBOTest.class);

    @BeforeClass
    public void beforeClass() {
        LOGGER.debug("beforeClass()");

        Object[][] zutatList = {
            {1, createZutat("Paprika") },
            {2, createZutat("Salami") },
            {3, createZutat("Käse") },
        };

        IdDAO<ZutatBO> dao = DAOFactory.getZutat();

        for (int i = 0; i < zutatList.length; i++) {
            ZutatBO as = (ZutatBO) zutatList[i][1];
            dao.save(as);
        }
        LOGGER.debug("beforeClass(done)");
    }

    @AfterClass
    public void afterClass() {
        LOGGER.debug("afterClass()");
        IdDAO<ZutatBO> dao = DAOFactory.getZutat();
        List<ZutatBO> asList = dao.get();
        for (ZutatBO as : asList) {
            dao.remove(as.getId());
        }
        LOGGER.debug("afterClass(done)");
    }

    @Test(enabled = false)
    public void testGet() {
        IdDAO<ZutatBO> dao = DAOFactory.getZutat();
        List<ZutatBO> resultList = dao.get();
        assertEquals(resultList.size(), 3);
    }

}
