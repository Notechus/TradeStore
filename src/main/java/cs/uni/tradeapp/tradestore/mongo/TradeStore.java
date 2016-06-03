package cs.uni.tradeapp.tradestore.mongo;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.nodes.PersistentNode;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Notechus on 05/25/2016.
 */
@Component
public class TradeStore
{
	private static final Logger log = LoggerFactory.getLogger(TradeStore.class);
	//"mongodb://localhost:27017,localhost:37017,localhost:47017", "TradeStore"
	private static final String connectionString = "mongodb://localhost:27017,localhost:37017,localhost:47017";
	private static final String database = "TradeStore";

	@Autowired
	private MongoConnector mongoConnector;

	@Autowired
	private CuratorFramework curator;

	@Autowired
	private LeaderSelector leader;


	@Bean(initMethod = "start", destroyMethod = "close")
	public CuratorFramework createCuratorFramework()
	{
		return CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
	}

	@Bean(initMethod = "start", destroyMethod = "close")
	public LeaderSelector leaderSelector()
	{
		return new LeaderSelector(curator, "/trade-application/services/leader/election", new LeaderSelectorListener()
		{

			public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState)
			{
				log.info("State has changed - current state = {}", connectionState);
			}

			public void takeLeadership(CuratorFramework curatorFramework) throws Exception
			{
				log.info("Taken over leadership role");
			}
		});
	}

	public void execute()
	{
		leader.autoRequeue();
	}
}
