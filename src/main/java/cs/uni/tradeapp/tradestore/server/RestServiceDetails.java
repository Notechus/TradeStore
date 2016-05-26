package cs.uni.tradeapp.tradestore.server;

/**
 * Created by Notechus on 05/25/2016.
 */
public class RestServiceDetails
{
	private String version;

	public RestServiceDetails()
	{

	}

	public RestServiceDetails(final String version)
	{
		this.version = version;
	}

	public void setVersion(final String version)
	{
		this.version = version;
	}

	public String getVersion()
	{
		return version;
	}
}
