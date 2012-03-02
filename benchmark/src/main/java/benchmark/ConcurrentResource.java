package benchmark;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class ConcurrentResource implements Resource {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        lock.readLock().lock();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {

        }
        lock.readLock().unlock();
    }

    public void write() {
        lock.writeLock().lock();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {

        }
        lock.writeLock().unlock();
    }
}
