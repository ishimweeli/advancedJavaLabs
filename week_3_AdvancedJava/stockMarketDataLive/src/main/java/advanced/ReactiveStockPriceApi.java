
package advanced;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ReactiveStockPriceApi {
    private static final String API_KEY = "Q6CE7H4RMVXLBMIG"; // Replace with your actual API key
    private static final String BASE_URL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=" + API_KEY;
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Observable<String> getStockPrice(String symbol) {
        return Observable.fromCallable(() -> fetchStockPrice(symbol))
                .subscribeOn(Schedulers.io());
    }

    private String fetchStockPrice(String symbol) {
        String url = String.format(BASE_URL, symbol);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return symbol + ": API request failed - " + response.code();
            }

            String jsonData = response.body().string();
            JsonNode root = objectMapper.readTree(jsonData);
            JsonNode globalQuote = root.get("Global Quote");

            if (globalQuote == null || globalQuote.isEmpty()) {
                return symbol + ": Unable to fetch price (API limit may have been reached)";
            }

            JsonNode priceNode = globalQuote.get("05. price");
            if (priceNode == null) {
                return symbol + ": Price data not available";
            }

            String price = priceNode.asText();
            return symbol + ": $" + price;
        } catch (IOException e) {
            return symbol + ": Error fetching price - " + e.getMessage();
        } catch (Exception e) {
            return symbol + ": Unexpected error - " + e.getMessage();
        }
    }
}