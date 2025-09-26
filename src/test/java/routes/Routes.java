package routes;

public class Routes 
{
	public static final String BASE_URL = "https://fakestoreapi.com/";
		
	// Product Module Endpoints
		
	public static final String GET_ALL_PRODUCTS = "/products";
	public static final String GET_PRODUCT_BY_ID = "/products/{id}";
	public static final String GET_PRODUCT_WITH_LIMIT = "/products?limit={limit}";
	public static final String GET_PRODUCT_BY_SORT = "/products?sort={order}";
	public static final String GET_ALL_CATAGORIES = "/products/categories";
	public static final String GET_PRODUCT_WITH_CATAGORIES = "/products/category/{category}";
	public static final String PRODUCT_CREATE = "/products";
	public static final String PRODUCT_UPDATE = "/products/{id}";
	public static final String PRODUCT_DELETE = "/products/{id}";
		
	// Cart Module Endpoints
	
	// User Module Endpoints
	
}
