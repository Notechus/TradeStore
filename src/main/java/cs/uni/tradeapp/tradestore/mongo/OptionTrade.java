package cs.uni.tradeapp.tradestore.mongo;

/**
 * Created by Notechus on 05/26/2016.
 */
public class OptionTrade extends Trade
{
	private String id;
	private String underlying;
	private int quantity;

	public OptionTrade(String id, String underlying, int quantity)
	{
		this.id = id;
		this.underlying = underlying;
		this.quantity = quantity;
		this.tradeType = "EuropeanOption";
	}

	/* getters & setters*/
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

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
