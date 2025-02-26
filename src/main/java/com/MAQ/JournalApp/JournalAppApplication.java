package com.MAQ.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JournalAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(JournalAppApplication.class, args);

		//new journal appplication
	}

	@Bean
	public PlatformTransactionManager temp(MongoDatabaseFactory databaseFactory){
		return new MongoTransactionManager(databaseFactory);
	}

	@Bean
	public RestTemplate restTemplate(){
		return  new RestTemplate();
	}

}
