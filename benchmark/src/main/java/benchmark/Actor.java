package benchmark;

import java.util.concurrent.Callable;

/**
 * @author Tedikova O.
 * @version 1.0
 */
abstract public class Actor implements Callable<Long> {

    public Actor() {
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Long call() throws Exception {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            doSomething();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public abstract void doSomething();
}
