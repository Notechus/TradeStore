package cs.uni.tradeapp.tradestore.mongo;


import java.util.ArrayList;

/**
 * Created by Notechus on 05/25/2016.
 */
public class TradeStore
{
	private MongoConnector mongoConnector;
	private ArrayList<Option> options;
	private ArrayList<OptionTrade> optionTrades;
	private ArrayList<StockTrade> stockTrades;

	public TradeStore(String connectionString, String database)
	{
		//"mongodb://localhost:27017,localhost:37017,localhost:47017", "TradeStore"
		this.mongoConnector = new MongoConnector(connectionString, database);
		this.options = new ArrayList<>();
		this.optionTrades = new ArrayList<>();
		this.stockTrades = new ArrayList<>();
	}
}
