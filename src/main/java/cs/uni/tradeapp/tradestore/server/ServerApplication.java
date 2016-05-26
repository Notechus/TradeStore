package cs.uni.tradeapp.tradestore.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Notechus on 05/25/2016.
 */
@SpringBootApplication
@ComponentScan("cs.uni.tradeapp.tradestore")
public class ServerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ServerApplication.class, args);
	}
}
