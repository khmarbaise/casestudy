package com.soebes.casestudy;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.casestudy.bo.BestellungBO;
import com.soebes.casestudy.bo.FilialeBO;
import com.soebes.casestudy.bo.KundeBO;
import com.soebes.casestudy.bo.PizzaBO;
import com.soebes.casestudy.bo.PizzaGroesseBO;
import com.soebes.casestudy.bo.ZutatBO;
import com.soebes.casestudy.dao.DAOFactory;
import com.soebes.casestudy.dao.IdDAO;

public class BestellungBOTest extends BOTestBase {
    private static Logger LOGGER = Logger.getLogger(BestellungBOTest.class);

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

        KundeBO kunde = createKunden("Kunde1", "Adresse1", "telefonnummber1");
        IdDAO<KundeBO> daoKunde = DAOFactory.getKunde();
        daoKunde.save(kunde);

        FilialeBO filiale = createFiliale("Filiale1");
        IdDAO<FilialeBO> daoFiliale = DAOFactory.getFiliale();
        daoFiliale.save(filiale);

        PizzaBO pizza = createPizza(pg, zutatenListe1);
        IdDAO<PizzaBO> daoPizza = DAOFactory.getPizza();
        daoPizza.save(pizza);

        Object[][] bestellListe = {
            {1, createBestellung(kunde, filiale, pizza, new Date()) },
        };

        IdDAO<BestellungBO> daoBestellung = DAOFactory.getBestellung();
        for (int i = 0; i < bestellListe.length; i++) {
            BestellungBO bestellung = (BestellungBO) bestellListe[i][1];
            daoBestellung.save(bestellung);
            LOGGER.info("Saved Bestellung:" + bestellung.getId());
        }

        LOGGER.debug("beforeClass(done)");
    }

//    @AfterClass
//    public void afterClass() {
//        LOGGER.debug("afterClass()");
//
//        deletePizza();
//
//        deleteZutaten();
//
//        deletePizzaGroesse();
//
//        deleteBestellungen();
//
//        LOGGER.debug("afterClass(done)");
//    }

    private void deleteBestellungen() {
        IdDAO<BestellungBO> dao = DAOFactory.getBestellung();
        List<BestellungBO> bestellListe = dao.get();
        for (BestellungBO item : bestellListe) {
            LOGGER.info("Delete Bestellung: " + item.getId());
            dao.remove(item);
        }
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

    @Test(enabled = true)
    public void testGet() {
        IdDAO<BestellungBO> dao = DAOFactory.getBestellung();
        List<BestellungBO> resultList = dao.get();
        assertEquals(resultList.size(), 1);
        LOGGER.info("Bestellung:" + resultList.get(0).getId());
    }


}
