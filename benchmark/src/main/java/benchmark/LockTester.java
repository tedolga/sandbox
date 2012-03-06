package benchmark;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class LockTester {

    private int threadCount;

    public static void main(String[] args) {
        final Resource syncResource = new SynchronizedResource();
        final Resource concurrentResource = new ConcurrentResource();
        LockTester tester = new LockTester();
        tester.setThreadCount(10);
        TestResult syncReadersTest = tester.testReaders(syncResource, 1000, "Sync resource reading");
        TestResult concurrentReadersTest = tester.testReaders(concurrentResource, 1000, "Concurrent resource reading");
        TestResult syncWritersTest = tester.testWriters(syncResource, 1000, "Sync resource writing");
        TestResult concurrentWritersTest = tester.testWriters(concurrentResource, 1000, "Concurrent resource writing");
        System.out.println(syncReadersTest.toString());
        System.out.println("---------------------------------------------------------------");
        System.out.println(concurrentReadersTest.toString());
        System.out.println("---------------------------------------------------------------");
        System.out.println(syncWritersTest.toString());
        System.out.println("---------------------------------------------------------------");
        System.out.println(concurrentWritersTest.toString());
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
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

    private static class Writer implements Callable {
        private Resource resource;
        private long operationTime;

        public Writer(Resource resource, long readingTime) {
            this.resource = resource;
            this.operationTime = readingTime;
        }

        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            resource.write(operationTime);
            long endTime = System.currentTimeMillis();
            return endTime - startTime;
        }
    }

    private List<Callable<Long>> initializeReaders(Resource resource, long operationTime) {
        List<Callable<Long>> readersList = new LinkedList<Callable<Long>>();
        for (int i = 0; i < threadCount; i++) {
            readersList.add(new Reader(resource, operationTime));
        }
        return readersList;
    }

    private List<Callable<Long>> initializeWriters(Resource resource, long operationTime) {
        List<Callable<Long>> readersList = new LinkedList<Callable<Long>>();
        for (int i = 0; i < threadCount; i++) {
            readersList.add(new Writer(resource, operationTime));
        }
        return readersList;
    }

    public TestResult testReaders(Resource resource, long readingTime, String testName) {
        List<Callable<Long>> readers = initializeReaders(resource, readingTime);
        return testLockTime(readingTime, testName, readers);
    }

    public TestResult testWriters(Resource resource, long writingTime, String testName) {
        List<Callable<Long>> readers = initializeWriters(resource, writingTime);
        return testLockTime(writingTime, testName, readers);
    }

    private TestResult testLockTime(long operationTime, String testName, List<Callable<Long>> tasks) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<Long>> results = new LinkedList<Future<Long>>();
        try {
            results = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestResult result = new TestResult(testName);
        result.setThreadCount(threadCount);
        result.setTimeout(operationTime);
        List<Long> durations = new ArrayList<Long>();
        long totalTime = 0;
        for (Future<Long> next : results) {
            try {
                durations.add(next.get());
                totalTime += next.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        result.setDurations(durations);
        result.setTotalTime(totalTime);
        return result;
    }

}
