package LoadBalancer;

public interface LoadBalancer {

    public Node findNode(String requestId);

    public void display();
}
