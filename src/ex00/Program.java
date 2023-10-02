package ex00;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int count = 50;
        if (args.length > 0) {
            for (String arg : args) {
                try (Scanner parser = new Scanner(arg);) {
                    parser.useDelimiter("=");
                    if (parser.hasNext() &&
                        parser.next().equals("--count") &&
                        parser.hasNextInt()) {
                        count = parser.nextInt();
                        if (count < 0) {
                            System.err.println("Negative count value");
                            System.exit(-1);
                        }
                    }   
                }               
            }

        }
        Philosopher egg = new Philosopher("Egg", count);
        Philosopher hen = new Philosopher("Hen", count);
        egg.start();
        hen.start();
        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            System.out.println("what the fuck");
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}