package LoadBalancer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Node {
    private String IpAddress;
    private int ram;

}
