package pl.exchangeapp.outputfiles;

import java.io.FileWriter;

public class FileOperations {

    public void saveTransactionHistoryToFile(String data) {
        try {
            FileWriter fw = new FileWriter("savetransactions/transactionshistory.txt");
            fw.write(data);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
    }
}
