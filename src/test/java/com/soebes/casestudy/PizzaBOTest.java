package com.soebes.casestudy;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.PizzaBO;
import com.soebes.casestudy.bo.PizzaGroesseBO;
import com.soebes.casestudy.bo.ZutatBO;
import com.soebes.casestudy.dao.DAOFactory;
import com.soebes.casestudy.dao.IdDAO;

public class PizzaBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(PizzaBOTest.class);

    @BeforeClass
    public void beforeClass() {
        LOGGER.debug("beforeClass()");

        List<ZutatBO> zutatenListe1 = new ArrayList<ZutatBO>();
        zutatenListe1.add(createZutat("Zutat1"));
        zutatenListe1.add(createZutat("Zutat2"));

        IdDAO<PizzaGroesseBO> daoPizzaGroesse = DAOFactory.getPizzaGroesse();
        PizzaGroesseBO pg = createPizzaGroesse("Size1");
        daoPizzaGroesse.save(pg);
        LOGGER.info("PizzaGroesse:" + pg.getId());

        IdDAO<ZutatBO> daoZutat = DAOFactory.getZutat();
        for (ZutatBO zutat : zutatenListe1) {
            daoZutat.save(zutat);
            LOGGER.info("Zutat:" + zutat.getId());
        }

        Object[][] pizzaList = {
            {1, createPizza(pg, zutatenListe1) },
        };

        IdDAO<PizzaBO> daoPizza = DAOFactory.getPizza();

        for (int i = 0; i < pizzaList.length; i++) {
            PizzaBO pizza = (PizzaBO) pizzaList[i][1];
            daoPizza.save(pizza);
            LOGGER.info("Saved Pizza:" + pizza.getId());
        }
        LOGGER.debug("beforeClass(done)");
    }

    @AfterClass
    public void afterClass() {
        LOGGER.debug("afterClass()");

        deletePizza();

        deleteZutaten();

        deletePizzaGroesse();

        LOGGER.debug("afterClass(done)");
    }

    private void deletePizzaGroesse() {
        IdDAO<PizzaGroesseBO> daoPizzaGroesse = DAOFactory.getPizzaGroesse();
        List<PizzaGroesseBO> asPG = daoPizzaGroesse.get();
        for (PizzaGroesseBO item : asPG) {
            daoPizzaGroesse.remove(item);
        }
    }

    private void deleteZutaten() {
        IdDAO<ZutatBO> daoZutat = DAOFactory.getZutat();
        for(ZutatBO zutat : daoZutat.get()) {
            daoZutat.remove(zutat);
        }
    }

    private void deletePizza() {
        IdDAO<PizzaBO> daoPizza = DAOFactory.getPizza();
        List<PizzaBO> pizzaList = daoPizza.get();
        LOGGER.info("deletePizza(): pizzaList:" + pizzaList.size());
        for (PizzaBO item : pizzaList) {
            LOGGER.info("Delete Pizza: " + item.getId());
            daoPizza.remove(item);
        }
    }

    @Test(enabled = false)
    public void testGet() {
        IdDAO<PizzaBO> dao = DAOFactory.getPizza();
        List<PizzaBO> resultList = dao.get();
        assertEquals(resultList.size(), 1);
        LOGGER.info("Pizza:" + resultList.get(0).getId());
        List<ZutatBO> zutaten = resultList.get(0).getZutatenListe();
        assertEquals(zutaten.size(), 2);
    }

}
