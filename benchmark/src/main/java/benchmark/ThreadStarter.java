package benchmark;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class ThreadStarter {

    public static void main(String[] args) {
        Resource resource = new SynchronizedResource();
        Writer writer = new Writer();
        writer.setResource(resource);
        Thread threadWriter=new Thread(writer);
        threadWriter.start();
    }
}
