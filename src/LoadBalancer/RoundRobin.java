package LoadBalancer;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

/**
 *  Round robin distributes the traffic equally across all nodes it just wraps around the nodes
 *  as the node handles 10 request request advances to next node
 *
 */
public class RoundRobin extends AbstractLoadBalancer{

    int index = 0;
    int count = 0;

    public RoundRobin(List<Node> cluster) throws InvalidArgumentException {
        super(cluster);
    }
    @Override
    public Node findNode(String requestId) {
        if(count == MIN_REQUEST_HANDLE){
           count = 0;
           index = (index+1)%cluster.size();
       }
        count++;
       Node n = cluster.get(index);
       request_to_node.put(requestId, n);
       node_traffic.put(n, node_traffic.getOrDefault(n,0)+1);
       return n;
    }
}
