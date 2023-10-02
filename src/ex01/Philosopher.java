package ex01;

public class Philosopher extends Thread {
    private String twohead;
    private int count;
    private Listener poorGuy;

    public Philosopher(String repeats, int times, Listener victim) {
        twohead = repeats;
        count = times;
        poorGuy = victim;
    }

    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                poorGuy.listen(twohead);
            }
        } catch (InterruptedException e) {
            System.out.println("Dies of cringe");
        }
    }
}
