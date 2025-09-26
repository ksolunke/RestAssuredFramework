package testcases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import payloads.Payload;
import pojo.Product;
import routes.Routes;

public class ProductDataDrivenTesting extends BaseClass
{
	@Test(dataProvider="jsonDataProvider", dataProviderClass=utils.DataProviders.class)
	public void addNewProduct(Map<String, String> data)
	{
		String title = data.get("title");
		double price = Double.parseDouble(data.get("price"));
		String description = data.get("description");
		String category = data.get("category");
		String image = data.get("image");
		
		Product product = new Product(title, price, description, image, category);
		
		int productid = given()
			.body(product)
			.contentType(ContentType.JSON)
			
		.when()
			.post(Routes.PRODUCT_CREATE)
	
		.then()
			.statusCode(201)
			//.log().body();
			.body("id", notNullValue())
			.body("title", equalTo(product.getTitle()))
			.extract().jsonPath().getInt("id"); // Get id from response
			//.log().all();
		
		System.out.println("Product Id : ==============>>>>>>>" + productid);
		
		given()
		.pathParam("id", productid)
		
		.when()
			.delete(Routes.PRODUCT_DELETE)

		.then()
			.statusCode(200);
			//.log().all();
		
		System.out.println("After Deletion Product Id : ==============>>>>>>>" + productid);
	}
}
