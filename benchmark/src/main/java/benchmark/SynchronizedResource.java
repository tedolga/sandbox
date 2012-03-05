package benchmark;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class SynchronizedResource implements Resource {

    public synchronized void read(long readTime){
        try {
            Thread.sleep(readTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void write(long writeTime) {
        try {
            Thread.sleep(writeTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
