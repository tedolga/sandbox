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
    }

    private class Reader implements Callable {
        private Resource resource;
        private long readingTime;

        public Reader(Resource resource, long readingTime) {
            this.resource = resource;
            this.readingTime = readingTime;
        }

        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            resource.read(readingTime);
            long endTime = System.currentTimeMillis();
            return endTime - startTime;
        }
    }
}
