package pl.exchangeapp.controller;

import java.util.Scanner;

public class ScannerProvider {
    private static ScannerProvider scannerProviderInstance;
    private Scanner scanner;

    private ScannerProvider() {
        scanner = new Scanner(System.in);
    }

    public static ScannerProvider getInstance() {
        if (scannerProviderInstance == null)
            scannerProviderInstance = new ScannerProvider();

        return scannerProviderInstance;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
