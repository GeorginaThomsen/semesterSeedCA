package xml;

import deploy.DeploymentConfiguration;
import entity.currencyDailyService.Currency;
import entity.currencyDailyService.DailyExchangeRate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 This class converts the XML data into an appropriate format
 */
public class XMLHandler{

    List<DailyExchangeRate> list;
    XmlReaderDemo reader;

    public XMLHandler() {
        list = new ArrayList();
        reader = new XmlReaderDemo();
    }
    public List<DailyExchangeRate> getDailyExchangeRateList(){
        
        System.out.println("Returning the DailyExchangeRateList");
        return list;
    }
    public void processData() {

        list = new ArrayList();//create a new ArrayList();
        
        System.out.println("Entering the Process Data Method");
        
         List<String> rowData = null;
         rowData = getDailyExchangeRate_RowData();
        
        
        String code = "";
        String description = "";
        String rate = "";
        String date = rowData.get(4);//get the data 
        
        System.out.println("Entering the for loop which processes the Row Data ");

        for (int i = 5; i < rowData.size(); i++) {

            code = rowData.get(i);
            i++;
            description = rowData.get(i);
            i++;
            rate = rowData.get(i);
            list.add(new DailyExchangeRate(new Currency(code, description), date, rate));
        }
        
        System.out.println("Exiting the for loop which process the Row Data");
    }
    private List<String> getDailyExchangeRate_RowData() {
        List<String> rowData = null;
        
        
        System.out.println("Getting the Daily Exchange Rate Row Data");
        
        
        rowData = reader.scrapeDataFromNationalBanken();//1) get the list of data
        
        
        
        System.out.println("Returning the Scraped Row Data");
        
        return rowData;//3) return the list
    }

}
