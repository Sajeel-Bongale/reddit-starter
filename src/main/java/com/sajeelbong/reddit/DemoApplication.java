package com.sajeelbong.reddit;

import com.sajeelbong.reddit.entity.Customer;
import com.sajeelbong.reddit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication
@PropertySource("application.properties")
public class DemoApplication  implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private MongoOperations mongoOperations;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}

	@Override
	public void run(String ...args) throws Exception{
		try {
			if (mongoOperations.collectionExists("customer")){
				mongoOperations.insert("customer", "sajeel");
				System.out.println("***********************/n/n/n");
			}

		} catch (Exception e) {
			System.out.println("Caught exception");
		}
		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
	}



}
