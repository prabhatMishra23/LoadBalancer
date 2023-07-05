package LoadBalancer;

import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractLoadBalancer implements LoadBalancer{

    List<Node> cluster;
    Map<Node, Integer> node_traffic;
    Map<String, Node> request_to_node;

    public static final int MIN_REQUEST_HANDLE = 10;

    public AbstractLoadBalancer(@NotNull List<Node> cluster) throws InvalidArgumentException {
        if(cluster.size() == 0){
            throw new InvalidArgumentException(new String[]{"cluster has no nodes"});
        }
        this.cluster = cluster;
        request_to_node = new HashMap<String, Node>();
        node_traffic = new HashMap<Node, Integer>();
    }
    public Node findNode(String requestId) {
        return null;
    }

    public void display(){
        System.out.println("---------------------------------------------------------");
        for(Node n : node_traffic.keySet()){
            System.out.println(" Node :: "+n.getIpAddress()+ " handled "+ node_traffic.get(n)+ " requests ");
        }
        System.out.println("---------------------------------------------------------");
    }
}
