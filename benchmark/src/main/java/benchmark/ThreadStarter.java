package benchmark;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class ThreadStarter {

    private static final int NUMBER_OF_READERS = 1;

    public static void main(String[] args) {
        final Resource syncResource = new SynchronizedResource();
        final Resource concurrentResource = new ConcurrentResource();
        List<Callable<Long>> syncReaders;
        syncReaders = initializeReaders(syncResource, 1000);
        List<Callable<Long>> concurrentReaders;
        concurrentReaders = initializeReaders(concurrentResource, 1000);
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_READERS);
        List<Future<Long>> syncResults = new LinkedList<Future<Long>>();
        List<Future<Long>> concurrentResults = new LinkedList<Future<Long>>();
        try {
            syncResults = executorService.invokeAll(syncReaders);
            concurrentResults = executorService.invokeAll(concurrentReaders);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
    }

    private static class Reader implements Callable {
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

    private static List<Callable<Long>> initializeReaders(Resource resource, long readingTime) {
        List<Callable<Long>> readersList = new LinkedList<Callable<Long>>();
        for (int i = 0; i < NUMBER_OF_READERS; i++) {
            readersList.add(new Reader(resource, readingTime));
        }
        return readersList;
    }
}
