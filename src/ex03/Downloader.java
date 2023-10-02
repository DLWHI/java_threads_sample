package ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;


public class Downloader extends Thread {
    private BufferedReader urlPool;

    public Downloader(BufferedReader pool) {
        urlPool = pool;
    }

    public void download(String url, int no) 
            throws MalformedURLException, IOException {
        URL source = new URL(url);
        Path dest = Paths.get(source.getPath()).getFileName();
        System.out.printf("%s started download file no %d%n", getName(), no);
        try (InputStream in = source.openStream()) {
            Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.printf("%s finished download file no %d%n", getName(), no);
    }

    public void run() {
        try { 
            String urlLine = urlPool.readLine();
            while (urlLine != null) {
                try (Scanner parser = new Scanner(urlLine)) {
                    int no = parser.nextInt();
                    String url = parser.next();
                    download(url, no);
                }
                urlLine = urlPool.readLine();
            } 
        } catch (MalformedURLException e) {
            System.err.printf("%s dies of cringe url%n", getName());
        } catch (IOException e) {
            System.err.printf("%s dies of cringe%n", getName());
        }
    }
}
