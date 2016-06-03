package cs.uni.tradeapp.tradestore.spring;

import cs.uni.tradeapp.utils.spring.RestServiceDetails;
import cs.uni.tradeapp.utils.zookeeper.MyDistributedQueue;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * Created by Notechus on 05/29/2016.
 */
@org.springframework.context.annotation.Configuration
public class Configuration
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	private static final String REST_VERSION = "1.0";
	public static final String SERVICE_NAME = "trade-store";
	public static final String SERVICE_PATH = "/trade-application/services";

	@Value("${server.port:45000}")
	private Integer port;

	@Value("${server.host:localhost}")
	private String host;


	@Autowired
	private CuratorFramework curatorFramework;

	@Bean(initMethod = "start", destroyMethod = "close")
	public ServiceDiscovery<RestServiceDetails> serviceDiscovery() throws Exception
	{
		return ServiceDiscoveryBuilder.builder(RestServiceDetails.class)
				.basePath(SERVICE_PATH)
				.client(curatorFramework)
				.build();
	}

	@Bean()
	public ServiceInstance initServiceInstance() throws Exception
	{
		return ServiceInstance.builder()
				.uriSpec(new UriSpec("{scheme}://{address}:{port}/{name}"))
				.address(host)
				.port(port)
				.name(SERVICE_NAME)
				.payload(new RestServiceDetails(REST_VERSION))
				.build();
	}


}
