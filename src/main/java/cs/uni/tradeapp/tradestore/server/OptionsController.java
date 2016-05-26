package cs.uni.tradeapp.tradestore.server;

import cs.uni.tradeapp.tradestore.mongo.MongoConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Notechus on 05/26/2016.
 */
@RestController
public class OptionsController
{
	public static final String PATH = "/" + SpringConfiguration.SERVICE_NAME + "/options";

	@Value("${instancename:trade-store}")
	private String instanceName;

	@Autowired
	private MongoConnector mongoConnector;

	@RequestMapping(PATH)
	public String event(@RequestParam(value = "type", defaultValue = " ") String type,
						@RequestParam(value = "trader", defaultValue = " ") String trader)
	{
		return mongoConnector.run();
	}
}
