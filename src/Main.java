import LoadBalancer.Node;
import LoadBalancer.RoundRobin;
import LoadBalancer.WeightedRoundRobin;
import LoadBalancer.context.LoadBalancingStrategy;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidArgumentException {
        System.out.println("Starting Load Balancer!");
        //
        List<Node> cluster = new ArrayList<>();
        createNodes(cluster);
        LoadBalancingStrategy strategy1 = LoadBalancingStrategy.builder().loadBalancer(new WeightedRoundRobin(cluster)).build();
        for(int request=1; request<=100; request++){
            strategy1.routeRequest(String.valueOf(request));
        }
        strategy1.showStatus();

        LoadBalancingStrategy strategy2 = LoadBalancingStrategy.builder().loadBalancer(new RoundRobin(cluster)).build();
        for(int request=1; request<=100; request++){
            strategy2.routeRequest(String.valueOf(request));
        }
        strategy2.showStatus();
    }

    public static void createNodes(List<Node> cluster){
        Node node1 = Node.builder().IpAddress("172.30.0.1").ram(16).build();
        Node node2 = Node.builder().IpAddress("172.30.0.2").ram(32).build();
        Node node3 = Node.builder().IpAddress("172.30.0.3").ram(32).build();
        Node node4 = Node.builder().IpAddress("172.30.0.4").ram(64).build();
        Node node5 = Node.builder().IpAddress("172.30.0.5").ram(16).build();
        Node node6 = Node.builder().IpAddress("172.30.0.6").ram(16).build();
        Node node7 = Node.builder().IpAddress("172.30.0.7").ram(64).build();
        Node node8 = Node.builder().IpAddress("172.30.0.8").ram(32).build();
        cluster.add(node1);
        cluster.add(node2);
        cluster.add(node3);
        cluster.add(node4);
        cluster.add(node5);
        cluster.add(node6);
        cluster.add(node7);
        cluster.add(node8);
    }
}