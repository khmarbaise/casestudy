package com.soebes.casestudy.dao;

import com.soebes.casestudy.bo.BestellungBO;
import com.soebes.casestudy.bo.FilialeBO;
import com.soebes.casestudy.bo.KundeBO;
import com.soebes.casestudy.bo.PizzaBO;
import com.soebes.casestudy.bo.PizzaGroesseBO;
import com.soebes.casestudy.bo.ZutatBO;

/**
 * This is factory class to create the different DAO instances.
 *
 * @author Karl Heinz Marbase
 *
 */
public final class DAOFactory {

    public static IdDAO<FilialeBO> getFiliale() {
        return new IdDAO<FilialeBO>(FilialeBO.class);
    }

    public static IdDAO<KundeBO> getKunde() {
        return new IdDAO<KundeBO>(KundeBO.class);
    }

    public static IdDAO<PizzaBO> getPizza() {
        return new IdDAO<PizzaBO>(PizzaBO.class);
    }

    public static IdDAO<PizzaGroesseBO> getPizzaGroesse() {
        return new IdDAO<PizzaGroesseBO>(PizzaGroesseBO.class);
    }

    public static IdDAO<ZutatBO> getZutat() {
        return new IdDAO<ZutatBO>(ZutatBO.class);
    }

    public static IdDAO<BestellungBO> getBestellung() {
        return new IdDAO<BestellungBO>(BestellungBO.class);
    }
}
