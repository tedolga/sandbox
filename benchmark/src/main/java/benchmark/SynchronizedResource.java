package benchmark;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class SynchronizedResource extends AbstractResource {

    public SynchronizedResource(long readTime, long writeTime) {
        super(readTime, writeTime);
    }

    public synchronized void read() {
        try {
            Thread.sleep(readTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void write() {
        try {
            Thread.sleep(writeTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
