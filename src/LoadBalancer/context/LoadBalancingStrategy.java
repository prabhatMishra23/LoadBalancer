package LoadBalancer.context;

import LoadBalancer.LoadBalancer;
import LoadBalancer.Node;
import lombok.Builder;

@Builder
public class LoadBalancingStrategy {

    LoadBalancer loadBalancer;

    public void routeRequest(String requestId){
        Node n = loadBalancer.findNode(requestId);
        System.out.println("Routing request "+requestId+ " to node "+n.getIpAddress());
    }

    public void showStatus(){
        loadBalancer.display();
    }

}
