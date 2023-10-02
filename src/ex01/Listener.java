package ex01;

public class Listener {
    private String lastWord = "";

    public synchronized void listen(String argument) throws InterruptedException {
        while (lastWord.equals(argument)) {
            wait();
        }
        System.out.println(argument);
        lastWord = argument;
        notify();
    }
}
