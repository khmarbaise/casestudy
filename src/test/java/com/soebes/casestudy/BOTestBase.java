package com.soebes.casestudy;

import java.util.Date;
import java.util.List;

import com.soebes.casestudy.bo.BestellungBO;
import com.soebes.casestudy.bo.FilialeBO;
import com.soebes.casestudy.bo.KundeBO;
import com.soebes.casestudy.bo.PizzaBO;
import com.soebes.casestudy.bo.PizzaGroesseBO;
import com.soebes.casestudy.bo.ZutatBO;

public class BOTestBase {

    protected PizzaGroesseBO createPizzaGroesse(String bezeichnung) {
        PizzaGroesseBO result = new PizzaGroesseBO();
        result.setBezeichnung(bezeichnung);
        result.setId(null);
        return result;
    }

    protected PizzaBO createPizza(PizzaGroesseBO pizzaGroesse, List<ZutatBO> zutatenListe) {
        PizzaBO result = new PizzaBO();
        result.setPizzaGroesse(pizzaGroesse);
        result.setZutatenListe(zutatenListe);
        result.setId(null);
        return result;
    }

    protected KundeBO createKunden(String name, String adresse, String telefonnummer) {
        KundeBO result = new KundeBO();
        result.setName(name);
        result.setAdresse(adresse);
        result.setTelefonnummer(telefonnummer);
        result.setId(null);
        return result;
    }

    protected FilialeBO createFiliale(String name) {
        FilialeBO result = new FilialeBO();
        result.setName(name);
        result.setId(null);
        return result;
    }

    protected ZutatBO createZutat(String bezeichnung) {
        ZutatBO result = new ZutatBO();
        result.setBezeichnung(bezeichnung);
        result.setId(null);
        return result;
    }

    protected BestellungBO createBestellung(KundeBO kunde, FilialeBO filiale, PizzaBO pizza, Date bestellDatum) {
        BestellungBO result = new BestellungBO();
        result.setBestellDatum(bestellDatum);
        result.setFiliale(filiale);
        result.setKunde(kunde);
        result.setPizza(pizza);
        result.setId(null);
        return result;
    }
}
