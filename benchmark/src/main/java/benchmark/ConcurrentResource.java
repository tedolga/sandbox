package benchmark;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class ConcurrentResource extends AbstractResource {

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public ConcurrentResource(long readTime, long writeTime) {
        super(readTime, writeTime);
    }

    public void read() {
        lock.readLock().lock();
        try {
            Thread.sleep(readTime);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        lock.writeLock().lock();
        try {
            Thread.sleep(writeTime);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
