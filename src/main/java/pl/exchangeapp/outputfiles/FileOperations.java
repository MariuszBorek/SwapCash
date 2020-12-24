package pl.exchangeapp.outputfiles;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.UUID;

public class FileOperations {

    public void saveTransactionHistoryToFile(String data) {

        try {
            FileWriter fw = new FileWriter("savetransactions/"+ LocalDate.now() +"_transactionshistory_" + UUID.randomUUID() +".txt");
            fw.write(data);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
    }
}
