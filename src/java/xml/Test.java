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
This is just a test class
 */
public class Test {

    public static void main(String[] args) {

        XMLHandler handler = new XMLHandler();
        EntityManager em = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME).createEntityManager();

        handler.processData();

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

                //em.persist(dailyExchangeRate);
            }
            em.getTransaction().commit();

        } finally {

            em.close();
        }

        //handler.processData();
//        for (DailyExchangeRate cr : handler.getDailyExchangeRateList()) {
//            
//            System.out.println("Currency Code: " + cr.getCurrency().getCode() +
//                    "|| CurrencyDescription: " + cr.getCurrency().getDescription() +
//                    "|| Date: " + cr.getDate_().getDate_() +
//                    "|| Rate: " + cr.getRate());
//            System.out.println("==========================================================================================================================");
//            
//        }
//        Persistence.generateSchema(DeploymentConfiguration.PU_NAME, null);
//
//        ExecutorService scheduler = Executors.newFixedThreadPool(1);
//        scheduler.execute(new XMLHandler());
//        scheduler.shutdown();
        //Persistence.generateSchema(DeploymentConfiguration.PU_NAME, null);
    }
}
