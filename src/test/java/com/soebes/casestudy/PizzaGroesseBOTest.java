package com.soebes.casestudy;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.PizzaGroesseBO;
import com.soebes.casestudy.dao.DAOFactory;
import com.soebes.casestudy.dao.IdDAO;

public class PizzaGroesseBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(PizzaGroesseBOTest.class);

    @BeforeClass
    public void beforeClass() {
        LOGGER.debug("beforeClass()");


        Object[][] pizzaGroesseList = {
            {1, createPizzaGroesse("Size1") },
            {2, createPizzaGroesse("Size2") },
            {3, createPizzaGroesse("Size3") },
            {4, createPizzaGroesse("Size4") },
            {5, createPizzaGroesse("Size5") },
            {6, createPizzaGroesse("Size6") },
            {7, createPizzaGroesse("Size7") },
            {8, createPizzaGroesse("Size8") },
        };

        IdDAO<PizzaGroesseBO> dao = DAOFactory.getPizzaGroesse();

        for (int i = 0; i < pizzaGroesseList.length; i++) {
            PizzaGroesseBO as = (PizzaGroesseBO) pizzaGroesseList[i][1];
            dao.save(as);
        }
        LOGGER.debug("beforeClass(done)");
    }

    @AfterClass
    public void afterClass() {
        LOGGER.debug("afterClass()");
        IdDAO<PizzaGroesseBO> dao = DAOFactory.getPizzaGroesse();
        List<PizzaGroesseBO> asList = dao.get();
        for (PizzaGroesseBO as : asList) {
            dao.remove(as.getId());
        }
        LOGGER.debug("afterClass(done)");
    }

    @Test(enabled = false)
    public void testGet() {
        IdDAO<PizzaGroesseBO> dao = DAOFactory.getPizzaGroesse();
        List<PizzaGroesseBO> resultList = dao.get();
        assertEquals(resultList.size(), 8);
    }

}
