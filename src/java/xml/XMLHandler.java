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
        return list;
    }
    public void processData() {

        list = new ArrayList();//create a new ArrayList();
         List<String> rowData = null;
         rowData = getDailyExchangeRate_RowData();

        String code = "";
        String description = "";
        String rate = "";
        String date = rowData.get(4);//get the data 

        for (int i = 5; i < rowData.size(); i++) {

            code = rowData.get(i);
            i++;
            description = rowData.get(i);
            i++;
            rate = rowData.get(i);
            list.add(new DailyExchangeRate(new Currency(code, description), date, rate));
        }
    }
    private List<String> getDailyExchangeRate_RowData() {
        List<String> rowData = null;
        rowData = reader.scrapeDataFromNationalBanken();//1) get the list of data
        return rowData;//3) return the list
    }

}
