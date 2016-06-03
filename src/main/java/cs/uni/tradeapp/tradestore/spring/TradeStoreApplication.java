package cs.uni.tradeapp.tradestore.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Notechus on 05/29/2016.
 */
@SpringBootApplication
@ComponentScan("cs.uni.tradeapp.tradestore")
public class TradeStoreApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(TradeStoreApplication.class, args);
	}
}
