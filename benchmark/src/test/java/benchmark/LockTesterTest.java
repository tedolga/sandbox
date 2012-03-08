package benchmark;

import org.junit.Test;

/**
 * @author Tedikova O.
 * @version 1.0
 */
public class LockTesterTest {
    @Test
    public void testOnlyReading() throws Exception {
        runReaders(0, 100);
        runReaders(10, 100);
        runReaders(10, 1);
    }

    private void runReaders(long sleepTime, int threadCount) throws Exception {
        final Resource syncResource = new SynchronizedResource(sleepTime, sleepTime);
        final Resource concurrentResource = new ConcurrentResource(sleepTime, sleepTime);
        LockTester tester = new LockTester();
        TestResult syncReadersTest = tester.testLockTime("Sync resource readers", tester.initializeReaders(syncResource, threadCount));
        TestResult concurrentReadersTest = tester.testLockTime("Concurrent resource writing", tester.initializeReaders(concurrentResource, threadCount));
        System.out.println(syncReadersTest.toString());
        System.out.println("---------------------------------------------------------------");
        System.out.println(concurrentReadersTest.toString());
        System.out.println("---------------------------------------------------------------");
    }

    private void runWriters(long sleepTime, int threadCount) throws Exception {
        final Resource syncResource = new SynchronizedResource(sleepTime, sleepTime);
        final Resource concurrentResource = new ConcurrentResource(sleepTime, sleepTime);
        LockTester tester = new LockTester();
        TestResult syncWritersTest = tester.testLockTime("Sync resource writing", tester.initializeWriters(syncResource, threadCount));
        TestResult concurrentWritersTest = tester.testLockTime("Concurrent resource writing", tester.initializeWriters(concurrentResource, threadCount));
        System.out.println(syncWritersTest.toString());
        System.out.println("---------------------------------------------------------------");
        System.out.println(concurrentWritersTest.toString());
        System.out.println("---------------------------------------------------------------");
    }
}
