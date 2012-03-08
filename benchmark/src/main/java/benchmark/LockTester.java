package benchmark;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class LockTester {

    public List<Callable<Long>> initializeReaders(final Resource resource, int readersCount) {
        List<Callable<Long>> readersList = new LinkedList<Callable<Long>>();
        for (int i = 0; i < readersCount; i++) {
            readersList.add(new Actor() {
                @Override
                public void doSomething() {
                    resource.read();
                }
            });
        }
        return readersList;
    }

    public List<Callable<Long>> initializeWriters(final Resource resource, int writersCount) {
        List<Callable<Long>> readersList = new LinkedList<Callable<Long>>();
        for (int i = 0; i < writersCount; i++) {
            readersList.add(new Actor() {
                @Override
                public void doSomething() {
                    resource.write();
                }
            });
        }
        return readersList;
    }

    public List<Callable<Long>> initializeWritersAndReaders(final Resource resource, int writersCount, int readersCount) {
        List<Callable<Long>> actorsList = new LinkedList<Callable<Long>>();
        for (int i = 0; i < writersCount; i++) {
            actorsList.add(new Actor() {
                @Override
                public void doSomething() {
                    resource.write();
                }
            });
        }
        for (int i = 0; i < readersCount; i++) {
            actorsList.add(new Actor() {
                @Override
                public void doSomething() {
                    resource.read();
                }
            });
        }
        return actorsList;
    }

    public TestResult testLockTime(String testName, List<Callable<Long>> tasks) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(tasks.size());
        TestResult result;
        try {
            List<Future<Long>> results = executorService.invokeAll(tasks);
            result = new TestResult(testName);
            result.setThreadCount(tasks.size());
            Deque<Long> durations = new LinkedList<Long>();
            long totalTime = 0;
            for (Future<Long> next : results) {
                durations.addLast(next.get());
                totalTime += next.get();
            }
            result.setDurations(durations);
            result.setTotalTime(totalTime);
        } finally {
            executorService.shutdown();
        }
        return result;
    }

}
