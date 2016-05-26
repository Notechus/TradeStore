package cs.uni.tradeapp.tradestore.mongo;

/**
 * Created by Notechus on 05/26/2016.
 */
public class StockTrade extends Trade
{
	private String underlying;
	private int quantity;

	public StockTrade(String underlying, int quantity)
	{
		this.underlying = underlying;
		this.quantity = quantity;
		this.tradeType = "Stock";
	}

	/* getters & setters*/
	public String getUnderlying()
	{
		return underlying;
	}

	public void setUnderlying(String underlying)
	{
		this.underlying = underlying;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
}
