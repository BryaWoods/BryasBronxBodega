package com.pluralsight.classes.files;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {

    private static final String RECEIPTS_FOLDER_PATH = "./Receipts/";

    public void createReceiptFile(String orderDetails, double totalCost) {
        LocalDateTime currentTime = LocalDateTime.now();
        String filename = currentTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss")) + ".txt";
        String filePath = RECEIPTS_FOLDER_PATH + filename;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Order Details:");
            writer.println(orderDetails);
            writer.println("Total Cost: $" + totalCost);
            System.out.println("Receipt file created: " + filename);
        } catch (IOException e) {
            System.err.println("Error creating receipt file: " + e.getMessage());
        }
    }
}
