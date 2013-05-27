package com.soebes.casestudy;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.KundeBO;
import com.soebes.casestudy.dao.DAOFactory;
import com.soebes.casestudy.dao.IdDAO;

public class KundeBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(KundeBOTest.class);

    @BeforeClass
    public void beforeClass() {
        LOGGER.debug("beforeClass()");


        Object[][] kundeList = {
            {1, createKunden("Kunde1", "Adresse1", "Telefon1") },
            {2, createKunden("Kunde2", "Adresse2", "Telefon2") },
            {3, createKunden("Kunde3", "Adresse3", "Telefon3") },
            {4, createKunden("Kunde4", "Adresse4", "Telefon4") },
            {5, createKunden("Kunde5", "Adresse5", "Telefon5") },
            {6, createKunden("Kunde6", "Adresse6", "Telefon6") },
        };

        IdDAO<KundeBO> dao = DAOFactory.getKunde();

        for (int i = 0; i < kundeList.length; i++) {
            KundeBO as = (KundeBO) kundeList[i][1];
            dao.save(as);
        }
        LOGGER.debug("beforeClass(done)");
    }

    @AfterClass
    public void afterClass() {
        LOGGER.debug("afterClass()");
        IdDAO<KundeBO> dao = DAOFactory.getKunde();
        List<KundeBO> asList = dao.get();
        for (KundeBO as : asList) {
            dao.remove(as.getId());
        }
        LOGGER.debug("afterClass(done)");
    }

    @Test(enabled = false)
    public void testGet() {
        IdDAO<KundeBO> dao = DAOFactory.getKunde();
        List<KundeBO> resultList = dao.get();
        assertEquals(resultList.size(), 6);
    }

}
