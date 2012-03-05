package benchmark;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public interface Resource {

    public void read(long readTime);

    public void write(long writeTime);

}
