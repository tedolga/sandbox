package benchmark;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class Writer implements Runnable {

    private Resource resource;

    public void run() {
        resource.write();
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
