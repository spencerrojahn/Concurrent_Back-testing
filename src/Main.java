import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
       
        Writer writer = new Writer("../data/results.txt");

        int[] buyRSIs = IntStream.rangeClosed(25, 55).toArray();
        int[] sellRSIs = IntStream.rangeClosed(40, 70).toArray();
        final int n = buyRSIs.length;
        final int m = sellRSIs.length;

        ExecutorService executor1 = Executors.newFixedThreadPool(n*m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (sellRSIs[j] > buyRSIs[i]) {
                    executor1.execute(new Thread(new Worker(buyRSIs[i], sellRSIs[j], writer)));
                }
                
            }
        }
        executor1.shutdown();
        executor1.awaitTermination(60, TimeUnit.SECONDS);

        // ExecutorService executor2 = Executors.newFixedThreadPool((n-n/2)*m);
        // for (int i = n/2; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         executor2.execute(new Thread(new Worker(buyRSIs[i], sellRSIs[j], writer)));
        //     }
        // }
        // executor2.shutdown();
        // executor2.awaitTermination(60, TimeUnit.SECONDS);

        writer.closeWriter();
    }
}

