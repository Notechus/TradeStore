package cs.uni.tradeapp.tradestore.mongo;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import cs.uni.tradeapp.utils.data.Option;
import cs.uni.tradeapp.utils.data.OptionTrade;
import cs.uni.tradeapp.utils.data.StockTrade;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Notechus on 05/25/2016.
 */
public class MongoConnector
{
	private MongoClient mongoClient;
	private MongoDatabase db;
	private final String ISO_DATE_FORMAT = "YYYY-MM-DD'T'HH:mm:ssZ";
	private SimpleDateFormat simpleDateFormat;
	private DateTimeFormatter format = DateTimeFormatter.ISO_INSTANT;

	public MongoConnector(String connectionString, String dbname)
	{
		MongoClientURI con = new MongoClientURI(connectionString);
		mongoClient = new MongoClient(con);
		simpleDateFormat = new SimpleDateFormat(ISO_DATE_FORMAT);
		db = mongoClient.getDatabase(dbname);
	}

	public Option[] getOptions(String trader)
	{
		BasicDBObject query = new BasicDBObject();
		query.put("Trader", trader);
		MongoCollection<Document> options = db.getCollection("Options");
		MongoCursor<Document> cursor = options.find(query).iterator();
		ArrayList<Option> tmp = new ArrayList<>();
		while (cursor.hasNext())
		{
			Document d = cursor.next();
			Option o = new Option();
			o.setId(d.getObjectId("_id").toString());
			o.setMaturity(simpleDateFormat.format(d.getDate("Maturity")));
			o.setUnderlying(d.getString("Underlying"));
			o.setDirection(d.getString("Direction"));
			o.setStrike(d.getDouble("Strike"));
			o.setTrader(d.getString("Trader"));
			o.setNotional(d.getDouble("Notional"));
			tmp.add(o);
		}
		Option[] res = new Option[tmp.size()];
		tmp.toArray(res);
		return res;
	}

	public void postOption(Option o) throws ParseException
	{
		Document doc = new Document("Underlying", o.getUnderlying())
				.append("Direction", o.getDirection())
				.append("Maturity", simpleDateFormat.parse(o.getMaturity()))
				.append("Strike", o.getStrike())
				.append("Trader", o.getTrader())
				.append("Notional", o.getNotional());

		db.getCollection("Options").insertOne(doc);
	}


	public OptionTrade[] getOptionTrades(String trader)
	{
		BasicDBObject query = new BasicDBObject();
		query.put("Trader", trader);
		MongoCollection<Document> options = db.getCollection("OptionTrade");
		MongoCursor<Document> cursor = options.find(query).iterator();
		ArrayList<OptionTrade> tmp = new ArrayList<>();
		while (cursor.hasNext())
		{
			Document d = cursor.next();
			OptionTrade o = new OptionTrade();
			o.setId(d.getObjectId("_id").toString());
			o.setQuantity(d.getInteger("Quantity"));
			o.setUnderlying(d.getString("Underlying"));
			o.setTrader(d.getString("Trader"));
			tmp.add(o);
		}
		OptionTrade[] res = new OptionTrade[tmp.size()];
		tmp.toArray(res);
		return res;
	}

	public void postOptionTrade(OptionTrade o)
	{
		Document doc = new Document("Underlying", o.getUnderlying())
				.append("Id", o.getId())
				.append("Strike", o.getQuantity())
				.append("Trader", o.getTrader());

		db.getCollection("OptionTrade").insertOne(doc);
	}

	public StockTrade[] getStockTrades(String trader)
	{
		BasicDBObject query = new BasicDBObject();
		query.put("Trader", trader);
		MongoCollection<Document> stocks = db.getCollection("StockTrade");
		MongoCursor<Document> cursor = stocks.find(query).iterator();
		ArrayList<StockTrade> tmp = new ArrayList<>();
		while (cursor.hasNext())
		{
			Document d = cursor.next();
			StockTrade s = new StockTrade();
			s.setQuantity(d.getDouble("Quantity"));
			s.setUnderlying(d.getString("Underlying"));
			s.setTrader(d.getString("Trader"));
			tmp.add(s);
		}
		System.out.println("stocks: " + tmp.toString());
		StockTrade[] res = new StockTrade[tmp.size()];
		tmp.toArray(res);
		return res;
	}

	public void postStockTrade(StockTrade s)
	{
		Document doc = new Document("Underlying", s.getUnderlying())
				.append("Quantity", s.getQuantity())
				.append("Trader", s.getTrader());

		db.getCollection("StockTrade").insertOne(doc);
	}

	public void close()
	{
		mongoClient.close();
	}

}