package benchmark;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class SynchronizedResource implements Resource {

    public synchronized void read(Long readTime) throws InterruptedException {
            Thread.sleep(readTime);
    }

    public synchronized void write(Long writeTime) throws InterruptedException {
            Thread.sleep(writeTime);
    }

}
