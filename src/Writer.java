import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    
    private FileWriter writer;

    public Writer(String fileLocation) throws IOException {
        this.writer = new FileWriter(fileLocation);
    }

    public synchronized void writeResults(int buyRSI, int sellRSI, double profits, int numTrades) throws IOException {
        writer.write(String.format("%d,%d,%f,%d\n", buyRSI, sellRSI, profits, numTrades));
    }

    public void closeWriter() throws IOException {
        this.writer.close();
    }
}
