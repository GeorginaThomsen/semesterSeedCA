package entity.currencyDailyService;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author terziev
 */
@Entity
public class Currency implements Serializable {

    @Id
    private String code;

    private String description;
    
    @OneToMany(mappedBy = "currency")
    private List<DailyExchangeRate> dailyExchangeRates;

    public Currency() {
    }

    public Currency(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DailyExchangeRate> getDailyExchangeRates() {
        return dailyExchangeRates;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Currency{" + "code=" + code + ", description=" + description + '}';
    }

    
    
}
