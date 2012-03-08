package benchmark;

/**
 * @author Tedikova O.
 * @version 1.0
 */
public abstract class AbstractResource implements Resource {

    protected final long readTime;
    protected final long writeTime;

    public AbstractResource(long readTime, long writeTime) {
        this.readTime = readTime;
        this.writeTime = writeTime;
    }
}
