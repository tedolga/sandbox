package benchmark;

import java.util.concurrent.Callable;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class ThreadStarter {

    public static void main(String[] args) {
        final Resource syncResource = new SynchronizedResource();
        final Resource concurrentResource = new ConcurrentResource();

        Callable syncReader = new Callable<Long>() {
            public Long call() throws Exception {
                long startTime = System.currentTimeMillis();
                syncResource.read(1000);
                long endTime = System.currentTimeMillis();
                return endTime - startTime;
            }
        };

        Callable concurrentReader = new Callable<Long>() {
            public Long call() throws Exception {
                long startTime = System.currentTimeMillis();
                concurrentResource.read(1000);
                long endTime = System.currentTimeMillis();
                return endTime - startTime;
            }
        };


    }
}
