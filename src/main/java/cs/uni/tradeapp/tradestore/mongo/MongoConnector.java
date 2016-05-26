package cs.uni.tradeapp.tradestore.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Notechus on 05/25/2016.
 */
public class MongoConnector
{
	private MongoClient mongoClient;
	private MongoDatabase db;

	public MongoConnector(String connectionString, String dbname)
	{
		MongoClientURI con = new MongoClientURI(connectionString);
		mongoClient = new MongoClient(con);
		db = mongoClient.getDatabase(dbname);
	}

	public String run()
	{
		String output = "";
		MongoCollection<Document> options = db.getCollection("Option");
		MongoCursor<Document> cursor = options.find().iterator();
		try
		{
			while (cursor.hasNext())
			{
				output += cursor.next().toJson() + "\n";
			}
		} finally
		{
			cursor.close();
		}
		return output;
	}

	public MongoCollection<Document> getCollection(String name)
	{
		return db.getCollection(name);
	}

	public MongoCursor<Document> getCursor(String name)
	{
		return db.getCollection(name).find().iterator();
	}


	public void close()
	{
		mongoClient.close();
	}

}
