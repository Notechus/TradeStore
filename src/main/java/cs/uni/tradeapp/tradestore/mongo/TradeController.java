package cs.uni.tradeapp.tradestore.mongo;

import cs.uni.tradeapp.utils.data.Option;
import cs.uni.tradeapp.utils.data.OptionTrade;
import cs.uni.tradeapp.utils.data.StockTrade;
import cs.uni.tradeapp.utils.data.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Notechus on 06/03/2016.
 */
@RestController
public class TradeController
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	private static final String OPTION_PATH = "trade-store/api/trade/option";
	private static final String STOCK_PATH = "trade-store/api/trade/stock";

	@Autowired
	private MongoConnector mongo;

	@CrossOrigin
	@RequestMapping(path = OPTION_PATH, method = RequestMethod.GET)
	public OptionTrade[] getOption(@RequestParam(value = "trader") String trader) throws Exception
	{
		OptionTrade[] options = mongo.getOptionTrades(trader);
		for (OptionTrade i : options)
		{
			log.info(i.getUnderlying());
		}
		log.info("returning " + options);
		return options;
	}

	@CrossOrigin
	@RequestMapping(path = OPTION_PATH, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void postOption(@RequestBody OptionTrade trade)
	{
		log.info("POST " + trade.getUnderlying() + " , " + trade.getId() + " , " + trade.getQuantity() + " , " + trade.getTradeType() + " , " + trade.getTrader());
		mongo.postOptionTrade(trade);
	}

	@CrossOrigin
	@RequestMapping(path = STOCK_PATH, method = RequestMethod.GET)
	public StockTrade[] getStock(@RequestParam(value = "trader") String trader)
	{
		StockTrade[] stocks = mongo.getStockTrades(trader);
		for (StockTrade i : stocks)
		{
			log.info(i.getUnderlying());
		}
		log.info("returning " + stocks);
		return stocks;
	}

	@CrossOrigin
	@RequestMapping(path = STOCK_PATH, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void postStock(@RequestBody StockTrade trade)
	{
		log.info("POST " + trade.getUnderlying() + " , " + trade.getQuantity() + " , " + trade.getTradeType() + " , " + trade.getTrader());
		mongo.postStockTrade(trade);
	}
}