package advanced;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class StockPriceDashboard {
    public static void main(String[] args) {
        ReactiveStockPriceApi api = new ReactiveStockPriceApi();

        // Extended list of stock symbols
        String[] symbols = {
                "AAPL", "GOOGL", "MSFT", "AMZN", "FB", "TSLA", "NVDA", "JPM", "JNJ", "V",
                "PG", "UNH", "MA", "HD", "DIS", "PYPL", "BAC", "ADBE", "CMCSA", "NFLX",
                "XOM", "CSCO", "VZ", "INTC", "PFE", "ABT", "KO", "PEP", "T", "MRK"
        };

        System.out.println("Fetching stock prices for " + symbols.length + " symbols...");

        Observable.interval(0, 1, TimeUnit.MINUTES)
                .flatMap(tick -> Observable.fromArray(symbols)
                        .concatMap(symbol -> api.getStockPrice(symbol)
                                .delay(200, TimeUnit.MILLISECONDS) // Add a 200ms delay between each request
                                .onErrorReturn(error -> symbol + ": Error - " + error.getMessage())))
                .buffer(symbols.length)
                .subscribe(
                        prices -> {
                            System.out.println("\n--- Stock Prices Update ---");
                            prices.forEach(System.out::println);
                            System.out.println("----------------------------\n");
                        },
                        error -> System.err.println("Critical error: " + error.getMessage()),
                        () -> System.out.println("Completed")
                );

        // Keep the application running
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}