package cs.uni.tradeapp.tradestore.spring;

import cs.uni.tradeapp.utils.spring.RestServiceDetails;
import cs.uni.tradeapp.utils.spring.ServiceURLProvider;
import org.apache.curator.x.discovery.DownInstancePolicy;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.strategies.RoundRobinStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.concurrent.TimeUnit;


/**
 * Created by Notechus on 25.05.16.
 */

public class ClientServiceProvider implements ServiceURLProvider
{
	@Autowired
	private ServiceDiscovery<RestServiceDetails> discovery;

	@Autowired
	private ServiceProvider<RestServiceDetails> provider;

	@Bean(initMethod = "start", destroyMethod = "close")
	public ServiceProvider<RestServiceDetails> serviceProvider()
	{
		return discovery.serviceProviderBuilder()
				.providerStrategy(new RoundRobinStrategy<RestServiceDetails>())
				.downInstancePolicy(new DownInstancePolicy(1, TimeUnit.SECONDS, 1))
				.serviceName(Configuration.SERVICE_NAME)
				.build();
	}

	public Collection<String> getAllInstancesURLs() throws Exception
	{
		return null;
	}

	public String getInstanceURL() throws Exception
	{
		return provider.getInstance().buildUriSpec();
	}
}
