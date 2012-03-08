package benchmark;

import java.util.ArrayList;

/**
 * @author Tedikova O.
 * @version 1.0
 */
public class TestResult {
    private String testName = "";
    private int threadCount;
    private long timeout;
    private Iterable<Long> durations = new ArrayList<Long>();
    private long totalTime;

    public TestResult(String testName) {
        this.testName = testName;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(testName);
        result.append(" :\n");
        result.append("Thread count : ");
        result.append(threadCount);
        result.append("\n");
        result.append("Operation timeout : ");
        result.append(timeout);
        result.append("\n");
        result.append("Duration of each operations (ms) : [ ");
        for (long duration : durations) {
            result.append(duration);
            result.append(" ");
        }
        result.append("]\n");
        result.append("Total time (ms): ");
        result.append(totalTime);
        result.append("\n");
        return result.toString();
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public void setDurations(Iterable<Long> durations) {
        this.durations = durations;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
}
