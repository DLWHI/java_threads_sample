package ex01;

public class Program {
    public static void main(String[] args) {
        int count = 50;
        Listener cringeVictim = new Listener();
        Philosopher egg = new Philosopher("Egg", count, cringeVictim);
        Philosopher hen = new Philosopher("Hen", count, cringeVictim);
        hen.start();
        egg.start();
    }
}
