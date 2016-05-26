package cs.uni.tradeapp.tradestore.mongo;

/**
 * Created by Notechus on 05/25/2016.
 */
public class Main
{
	public static void main(String[] args)
	{
		MongoConnector mongoConnector = new MongoConnector("mongodb://localhost:27017,localhost:37017,localhost:47017", "TradeStore");
		mongoConnector.run();
		mongoConnector.close();
	}
}
