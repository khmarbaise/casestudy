package com.soebes.casestudy;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.FilialeBO;
import com.soebes.casestudy.dao.DAOFactory;
import com.soebes.casestudy.dao.IdDAO;

public class FilialeBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(FilialeBOTest.class);

    @BeforeClass
    public void beforeClass() {
        LOGGER.debug("beforeClass()");


        Object[][] filialeList = {
            {1, createFiliale("First") },
            {2, createFiliale("Second") },
            {3, createFiliale("Third") },
            {4, createFiliale("Forth") },
        };

        IdDAO<FilialeBO> dao = DAOFactory.getFiliale();

        for (int i = 0; i < filialeList.length; i++) {
            FilialeBO as = (FilialeBO) filialeList[i][1];
            dao.save(as);
        }
        LOGGER.debug("beforeClass(done)");
    }

    @AfterClass
    public void afterClass() {
        LOGGER.debug("afterClass()");
        IdDAO<FilialeBO> dao = DAOFactory.getFiliale();
        List<FilialeBO> asList = dao.get();
        for (FilialeBO as : asList) {
            dao.remove(as.getId());
        }
        LOGGER.debug("afterClass(done)");
    }

    @Test(enabled = false)
    public void testGet() {
        IdDAO<FilialeBO> dao = DAOFactory.getFiliale();
        List<FilialeBO> resultList = dao.get();
        assertEquals(resultList.size(), 4);
    }

}
