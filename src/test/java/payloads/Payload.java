package payloads;

import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Product;

public class Payload 
{
	Random random = new Random();
	// We need to create random data
	
	private static String categories[] = {"electronic", "clothing", "beauty", "furniture", "books"};
	
	
	public static Product productPayload()
	{
		Random random = new Random();
		
		Faker faker = new Faker(); // Declaration class 
		
		String name = faker.commerce().productName();
		
		double price = Double.parseDouble(faker.commerce().price());
		
		String description = faker.lorem().sentence();
		
		String imageUrl = "https://i.pravatar.cc";
		
		String category = categories[random.nextInt(categories.length)];
		
		Product product = new Product(name, price, description, imageUrl, category);
		
		return product;
	}
	
}
