/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import deploy.DeploymentConfiguration;
import entity.currencyDailyService.Currency;
import entity.currencyDailyService.DailyExchangeRate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * This class will perform the daily timer service
 */
public class TimerService implements Runnable {

    public void runTask() {

        EntityManager em = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME).createEntityManager();
        
        XMLHandler handler = new XMLHandler();

        handler.processData();//get the data and convert it 

        List<DailyExchangeRate> list = handler.getDailyExchangeRateList();
        try {
            em.getTransaction().begin();
            //loop through the list and insert each touple
            for (DailyExchangeRate dailyExchangeRate : list) {
                Currency c = em.find(Currency.class, dailyExchangeRate.getCurrency().getCode());
                if (c != null) {
                    em.persist(new DailyExchangeRate(c, dailyExchangeRate.getDate_(), dailyExchangeRate.getRate()));
                } else {
                    em.persist(dailyExchangeRate);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    @Override
    public void run() {
        runTask();
    }
}
