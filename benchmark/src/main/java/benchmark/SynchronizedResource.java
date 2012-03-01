package benchmark;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class SynchronizedResource implements Resource {
    private boolean isReading = false;
    private boolean isWriting = false;

    public synchronized void read() {

    }

    public synchronized void write() {
        for (int i = 0; i < 10; i++) {
            while (isReading || isWriting) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println("Can't write");
                }
            }
            isWriting = true;
            System.out.println(Thread.currentThread().getName() + " Writing");
        }
        isWriting = false;
        notifyAll();
    }

}
