package LoadBalancer;

import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
I need to distribute requests according to weight
I go around a circular buffer and I increment index only when quota of
current node get's over
to decide quota of current node  I need to ratio of request out of all nodes
I add minimum request that a server of ram 16GB can handle, say 10
and then divide capacity of server with 16 to know weight factor
and multiply that number of request
I need to  given request number what is index and return corresponding Node.
 */

/**
 *
 */
public class WeightedRoundRobin extends AbstractLoadBalancer{

    private static int request_number;
    private int[] requestSize;
    private int totalClusterCapacity;
    
    private static final int MIN_SERVER_RAM = 16;


    /**
     *
     * @param cluster
     * @throws InvalidArgumentException
     */
    public WeightedRoundRobin(@NotNull List<Node> cluster) throws InvalidArgumentException {
        super(cluster);
        requestSize = new int[cluster.size()];
        requestSize[0] = calculateRequestHandlingCapacity(cluster.get(0).getRam());
        for(int i=1; i<cluster.size(); i++){
            requestSize[i] = requestSize[i-1] + calculateRequestHandlingCapacity(cluster.get(i).getRam());
        }
        totalClusterCapacity = requestSize[cluster.size()-1];
    }

    /**
     *
     * @param memory
     * @return
     */
    private static int calculateRequestHandlingCapacity(int memory) {
        return (memory / MIN_SERVER_RAM) * MIN_REQUEST_HANDLE;
    }

    /**
     *
     * @return
     */
    @Override
    public Node findNode(String requestId) {
          request_number++;
          int request_position = request_number%totalClusterCapacity;
          int index = searchIndex(request_position);
          Node node = cluster.get(index);
          request_to_node.put(requestId, node);
          node_traffic.put(node, node_traffic.getOrDefault(node,0)+1);
          return node;
    }

    /**
     *
     * @param requestPosition
     * @return
     */
    private int searchIndex(int requestPosition) {
        int low = 0;
        int high = requestSize.length-1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(requestSize[mid] >= requestPosition){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return low;
    }

}
