/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.currencyDailyService.Currency;
import entity.currencyDailyService.DailyExchangeRate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Cookie
 */
public class CurrencyFacade {

    //AddCurrencyto db
    //Add currency entityclas/db
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    private EntityManager em = emf.createEntityManager();

    public void addCurr(List<Currency> currency) {
        em.getTransaction().begin();
        for (Currency c : currency) {
            em.persist(c);
        }
        em.getTransaction().commit();
    }

    public List<Currency> getCurr() {
        Query query = em.createQuery("SELECT c FROM Currency c", Currency.class);
        return query.getResultList();
    }
    public List<DailyExchangeRate> getCurr1() {
        Query query = em.createQuery("SELECT d FROM DailyExchangeRate d", DailyExchangeRate.class);
        return query.getResultList();
    }

    //Make calc:
    public Double Calculator(Double amount, String fromCurrency, String toCurrency) {
        Double from;
        Double to;
        Query query = em.createQuery("SELECT c currCode FROM Currency c");
        query.setParameter("currCode", Double.parseDouble(fromCurrency));
        from = (Double) query.getResultList().get(0);
        query.setParameter("currCode", Double.parseDouble(toCurrency));
        to = (Double) query.getResultList().get(0);
        Double exchangeRate = from / to;
        return exchangeRate * amount;

    }
}
