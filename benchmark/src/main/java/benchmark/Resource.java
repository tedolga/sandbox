package benchmark;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public interface Resource {

    public void read(Long readTime) throws InterruptedException;

    public void write(Long writeTime) throws InterruptedException;

}
