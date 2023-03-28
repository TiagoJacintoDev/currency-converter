import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConversionResponse {
    boolean success;

    @JsonIgnore
    Object query;
    String from;
    String to;
    double amount;

    @JsonIgnore
    Object info;
    long timestamp;
    double rate;

    String date;

    @JsonProperty("result")
    BigDecimal exchangeRate;

    @JsonIgnore
    Object error;
    String code;
    String message;
}
