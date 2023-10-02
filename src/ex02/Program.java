package ex02;

import java.util.Random;
import java.util.Scanner;

public class Program {
    private static int[] array;
    private static int arraySize = 13;
    private static int threadCount = 3;
    private static long threadSum = 0;
    private static long expected = 0;
    private static long start;
    public static void main(String[] args) {
        for (String arg : args) {
            try (Scanner parser = new Scanner(arg);) {
                parser.useDelimiter("=");
                String option = parser.next();
                String value = parser.next();
                if (option.equals("--arraySize")) {
                    arraySize = Integer.parseInt(value);
                } else if (option.equals("--threadsCount")) {
                    threadCount = Integer.parseInt(value);
                }
            }               
        }
        if (arraySize < 0 && arraySize > 2000000 && threadCount > arraySize) {
            System.err.println("Dies of overload");
            System.exit(-1);
        } else if (threadCount < 1) {
            System.err.println("Noone to calc");
            System.exit(-1);
        }
        array = new int[arraySize];
        fill();
        Adder[] adders = new Adder[threadCount];
        start = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            adders[i] = new Adder(
                    i*arraySize/threadCount,
                    (i + 1)*arraySize/threadCount,
                    array);
            adders[i].start(); 
        }
        try {
            for (int i = 0; i < adders.length; i++) {
                adders[i].join();
                threadSum += adders[i].acquire();
            }   
        } catch (Exception e) {
            System.err.println("Dies of overload");
            System.exit(254);
        }
        start = System.nanoTime() - start;
        System.out.printf("Sum: %d%n", expected);
        for (Adder adder : adders) {
            System.out.printf("%s%n", adder);
        }
        System.out.printf("Sum by threads: %d%n", threadSum);
        System.out.printf("Elapsed time %d ms%n", start/1000000);
    }

    public static void fill() {
        Random rnd = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(1001 + 1000) - 1000;
            expected += array[i];
        }
    } 
}
