package cs.uni.tradeapp.tradestore.mongo;

import java.time.LocalDateTime;

/**
 * Created by Notechus on 05/25/2016.
 */
public class Option
{
	private String id;
	private String underlying;
	private LocalDateTime maturity;
	private double notional;
	private double strike;
	private String trader;

	public Option(String id, String underlying, LocalDateTime maturity, double notional, double strike, String trader)
	{
		this.id = id;
		this.underlying = underlying;
		this.maturity = maturity;
		this.notional = notional;
		this.strike = strike;
		this.trader = trader;
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

	public LocalDateTime getMaturity()
	{
		return maturity;
	}

	public void setMaturity(LocalDateTime maturity)
	{
		this.maturity = maturity;
	}

	public double getNotional()
	{
		return notional;
	}

	public void setNotional(double notional)
	{
		this.notional = notional;
	}

	public double getStrike()
	{
		return strike;
	}

	public void setStrike(double strike)
	{
		this.strike = strike;
	}

	public String getTrader()
	{
		return trader;
	}

	public void setTrader(String trader)
	{
		this.trader = trader;
	}
}
