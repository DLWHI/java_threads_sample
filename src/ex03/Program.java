package ex03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    private static int threadCount = 3;
    private static String urlFile = "ex03/files_urls.txt";
    public static void main(String[] args) {
        for (String arg : args) {
            try (Scanner parser = new Scanner(arg);) {
                parser.useDelimiter("=");
                String option = parser.next();
                String value = parser.next();
                if (option.equals("--threadsCount")) {
                    threadCount = Integer.parseInt(value);
                } else if (option.equals("--url-file")) {
                    urlFile = value;
                }
            }
        }
        if (threadCount < 1 ) {
            System.err.println("Noone to download");
            System.exit(-1);
        }
        Downloader[] daemons = new Downloader[threadCount];
        try (BufferedReader urlPool = new BufferedReader(new FileReader(urlFile))) {
            for (int i = 0; i < daemons.length; i++) {
                daemons[i] = new Downloader(urlPool);
                daemons[i].start();
            }
            for (int i = 0; i < daemons.length; i++) {
                daemons[i].join();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find url file");
        } catch (InterruptedException | IOException e) {
            System.out.println("Someone dies of cringe");
        }
    }
}
