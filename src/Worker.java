import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {

    private int buyRSI;
    private int sellRSI;
    private double profits;
    private BufferedReader reader;
    private Writer writer;

    public Worker(int buyRSI, int sellRSI, Writer writer){
        this.buyRSI = buyRSI;
        this.sellRSI = sellRSI;
        this.profits = 0.0;
        this.writer = writer;

    }

    @Override
    public void run() {
        try {
            this.reader = new BufferedReader(new FileReader("../data/RSI_close.txt"));

            int pos = 0;
            double buyPrice = 0.0;
            double prevRSI = 1000.0;
            int numTrades = 0;

            String line = reader.readLine();
            while (line != null) {
                String[] row = line.split(",");
                double close = Double.parseDouble(row[1]);
                double rsi = Double.parseDouble(row[2]);

                if (pos == 0 && prevRSI < this.buyRSI && rsi >= this.buyRSI) {
                    buyPrice = close;
                    pos = 1;
                } else if (pos == 1 && prevRSI < this.sellRSI && rsi >= this.sellRSI) {
                    profits += (close - buyPrice);
                    pos = 0;
                    numTrades++;
                }

                prevRSI = rsi;
                line = reader.readLine();
            }

            this.reader.close();
            this.writer.writeResults(this.buyRSI, this.sellRSI, this.profits, numTrades);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

