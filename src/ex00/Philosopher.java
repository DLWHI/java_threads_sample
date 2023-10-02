package ex00;

public class Philosopher extends Thread {
    private String twohead;
    private int count;

    public Philosopher(String repeats, int times) {
        twohead = repeats;
        count = times;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(twohead);
        }
    }
}