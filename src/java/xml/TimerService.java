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
 This class will perform the daily timer service
 */
public class TimerService implements Runnable{
        
    EntityManager em = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME).createEntityManager();

    public void runTask(){
        
        XMLHandler handler = new XMLHandler();
        System.out.println("After XML Handler");
        
        System.out.println("-------------------------------------------------------");
        
        handler.processData();//get the data and convert it 
        
        System.out.println("After handler.processData();");
        
        System.out.println("------------------------------------------------------");
        
        
        List<DailyExchangeRate> list = handler.getDailyExchangeRateList();
 
        System.out.println("After handler.getDailyExchangeRateList()");
        
        System.out.println("-----------------------------------------------------");
        try {
            System.out.println("Beginning a transaction");
            
            em.getTransaction().begin();
            //loop through the list and insert each touple
            
            System.out.println("Entering the for loop for persisting the XML data");
            
            for (DailyExchangeRate dailyExchangeRate : list) {
                Currency c = em.find(Currency.class, dailyExchangeRate.getCurrency().getCode());
                if (c != null) {
                    em.persist(new DailyExchangeRate(c, dailyExchangeRate.getDate_(), dailyExchangeRate.getRate()));
                } else {
                    em.persist(dailyExchangeRate);
                }
            }
            System.out.println("Exiting the for loop for persisting the XML data");
            
            em.getTransaction().commit();
        } finally {
            System.out.println("Closing the transaction");
            em.close();
            
        }
        System.out.println("--------------------------------------------------");
        
    }
    
    @Override
    public void run() {
        System.out.println("==================================================================================");
        
        System.out.println("In the RUN method");
        
        runTask();
        
        System.out.println("Run method finished");
        
        System.out.println("======================================================================================");
    }  
}
