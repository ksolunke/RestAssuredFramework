package testcases;
import static io.restassured.RestAssured.*; // for accessing gherkin keywords
import static io.restassured.matcher.RestAssuredMatchers.*; //
import static org.hamcrest.Matchers.*; // Validation

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import routes.Routes;
import utils.configReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Product;

public class ProductTest extends BaseClass
{
	configReader configreader;

	// To get all product details
	@Test
	public void getAllProducts()
	{
		given()
		
		.when()
			//.get("https://fakestoreapi.com//products")
			.get(Routes.GET_ALL_PRODUCTS)
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	// To get specific product by its id.
	@Test
	public void getProductById() throws Exception
	{
		configReader configreader = new configReader();
		
		int productid = configreader.getIntProperty("productId");
		
		given()
			.pathParam("id", productid)
		
		.when()
			.get(Routes.GET_PRODUCT_BY_ID)
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	// Get Product with some limit.
	
	@Test
	public void getLimitedProduct()
	{
		given()
			.pathParam("limit", 3)
		//.queryParam("limit", 3)
	
		.when()
			.get(Routes.GET_PRODUCT_WITH_LIMIT)
	
		.then()
			.statusCode(200)
			.log().all();
	}
	
	// Get products in descending orders
	
	@Test
	public void getSortedProductDesc()
	{
		Response response = given()
			.pathParam("order", "desc")
	
		.when()
			.get(Routes.GET_PRODUCT_BY_SORT);
	
		List<Integer> productIds = response.jsonPath().getList("id",Integer.class);
		
		isSortedDescending(productIds);
		
		Assert.assertEquals(isSortedDescending(productIds), true);
			
	}
	
	
	// Get products in asc orders
	
		@Test
		public void getSortedProductAsc()
		{
			Response response = given()
				.pathParam("order", "asc")
		
			.when()
				.get(Routes.GET_PRODUCT_BY_SORT);
		
			List<Integer> productIds = response.jsonPath().getList("id",Integer.class);
			
			isSortedAscending(productIds);
			
			Assert.assertEquals(isSortedAscending(productIds), true);
				
		}
		
	// Get all categories 
		
		@Test
		public void getAllCategories()
		{
			given()
				
			.when()
				.get(Routes.GET_ALL_CATAGORIES)
		
			.then()
				.statusCode(200)
				.log().all();
					
		}
		
		//Get product with specific category
		
		@Test
		public void getProductByCategory()
		{
			given()
				.pathParam("category", "electronics")
			.when()
				.get(Routes.GET_PRODUCT_WITH_CATAGORIES)
		
			.then()
				.statusCode(200)
				.body("category", everyItem(equalTo("electronics")))
				.log().all();
					
		}
		
		//Create new product
		
		@Test
		public void createNewProduct()
		{
			Product product = Payload.productPayload(); // To create some random data
			
			//String title = product.getTitle();
			
			given()
				.body(product)
				.contentType(ContentType.JSON)
				
			.when()
				.post(Routes.PRODUCT_CREATE)
		
			.then()
				.statusCode(201)
				.body("id", notNullValue())
				.body("title", equalTo(product.getTitle()))
				//.extract().jsonPath().getInt("id"); // Get id from response
				.log().all();
							
		}
		
	//Update product
		
		@Test
		public void updateProduct() throws Exception
		{
			Product updatedProduct = Payload.productPayload(); // To create some random data
			configreader = new configReader();
			int productId = configreader.getIntProperty("productId");
			
			//String title = product.getTitle();
			
			given()
				.body(updatedProduct)
				.contentType(ContentType.JSON)
				.pathParam("id", productId)
				
			.when()
				.put(Routes.PRODUCT_UPDATE)
		
			.then()
				.statusCode(200)
				.body("title", equalTo(updatedProduct.getTitle()))
				.log().all();
								
		}
		
	//Delete product
		
		@Test
		public void deleteProduct() throws Exception 
		{
			configreader = new configReader();
			int productId = configreader.getIntProperty("productId");
		
			
			given()
				.pathParam("id", productId)
				
			.when()
				.delete(Routes.PRODUCT_DELETE)
		
			.then()
				.statusCode(200)
				.log().all();
								
		}
		
}
