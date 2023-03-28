import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HttpActions {
    public static ConversionResponse getConversion (String inputCurrency, BigDecimal inputCurrencyAmount, String outputCurrency)
            throws IOException, InterruptedException {
        var objectMapper = new ObjectMapper();

        String requestURI = "https://api.apilayer.com/exchangerates_data/convert" +
                "?to=" + outputCurrency +
                "&from=" + inputCurrency +
                "&amount=" + inputCurrencyAmount;

        var request = HttpRequest.newBuilder()
                .uri(URI.create(requestURI))
                .header("apikey", "43F8rOoffwng8yOX3UZhqcVV5aoImNNe")
                .header("Content-Type", "application/json")
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();

        var response = HttpClient
                .newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 400) {
            return null;
        }

        return objectMapper.readValue(response.body(), ConversionResponse.class);
    }
}
