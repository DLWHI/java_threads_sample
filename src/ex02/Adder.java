package ex02;

public class Adder extends Thread {
    int left;
    int right;
    int[] data;
    long result = 0;

    public Adder(int start, int end, int[] data) {
        left = start;
        right = end;
        this.data = data;
    }

    public void run() {
        for (int i = left; i < right && i < data.length; i++) {
            result += data[i];
        }
    }

    public long acquire() {
        return result;
    }

    public String toString() {
        return String.format(
            "%s: from %d to %d sum is %d", 
            getName(),
            left,
            (right > data.length) ? data.length : right - 1,
            result
        );
    }
}
