package Maybank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BatchJob {

    public static void main(String[] args) {
        String filePath = "C://Users/Apr25/Documents/dataSource.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split("\\|");
                
                Transaction transaction = new Transaction();
                transaction.setAccountNumber(fields[0]);
                transaction.setTrxAmount(Double.parseDouble(fields[1]));
                transaction.setDescription(fields[2]);
                transaction.setTrxDate(fields[3]);
                transaction.setTrxTime(fields[4]);
                transaction.setCustomerId(fields[5]);

                transactionRepository.save(transaction);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}