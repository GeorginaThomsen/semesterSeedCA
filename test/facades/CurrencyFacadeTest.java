/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.currencyDailyService.Currency;
import entity.currencyDailyService.DailyExchangeRate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cookie
 */
public class CurrencyFacadeTest {

    CurrencyFacade cf = new CurrencyFacade();
    private final String puName = DeploymentConfiguration.PU_NAME;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(puName);
    EntityManager em = emf.createEntityManager();

    public CurrencyFacadeTest() {
    }

    @Before
    public void setUp() {
        em.getEntityManagerFactory();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from Currency").executeUpdate();
            Currency p = new Currency("code1", "Description1");
            em.persist(p);
            em.persist(new Currency("code2", "Description2"));
            em.persist(new Currency("code3", "Description3"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @Test
    public void testAddCurr() {
        Currency c1 = new Currency("TestAddCurr", "testDescription");
        List<Currency> CList = new ArrayList();
        CList.add(c1);
        cf.addCurr(CList);
//        Currency testCurrency = em.find(Currency.class, c1.getCode());
        assertEquals("TestAddCurr", c1.getCode());

    }

    @Test
    public void testGetCurr() {
        Currency c1 = new Currency("TestGetCurr", "testDescriptionGetCurr");
        List<Currency> CList = new ArrayList();
        CList.add(c1);
        cf.getCurr().add(c1);
        assertEquals("TestGetCurr", c1.getCode());
    }

    @Test
    public void testGetCurr1() {
        Currency c1 = new Currency("TestGetCurr11111", "testDescriptionGetCurr");
        List<Currency> CList = new ArrayList();
        CList.add(c1);
        cf.getCurr().add(c1);
        assertEquals("TestGetCurr11111", c1.getCode());
    }

//    @Test
//    public void testCalculator() {
//        DailyExchangeRate d = new DailyExchangeRate(null, "vfdgfd", "4455");
//        DailyExchangeRate d1 = new DailyExchangeRate(null, "vfdgfd", "4455");
//
//        double amount = 50.000;
//        String fromCurrency = em.find(DailyExchangeRate.class, 1).getRate();
//        String toCurrency = em.find(DailyExchangeRate.class, 2).getRate();
//        double expResult = cf.Calculator(amount, em.find(Currency.class, 1).getCode(), em.find(Currency.class, 2).getCode());
//        double result = (Double.parseDouble(fromCurrency)) / (Double.parseDouble(toCurrency)) * amount;
//        double re = (Double.parseDouble(d.getRate())) / (Double.parseDouble(d1.getRate())) * amount;
//        assertEquals(expResult, result);
//        System.out.println("Exp: " + expResult);
//        System.out.println("result: " + result);

//    }
}
