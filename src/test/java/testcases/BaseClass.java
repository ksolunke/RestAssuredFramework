package testcases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routes.Routes;

public class BaseClass 
{
	RequestLoggingFilter requestloggingfilter;
	
	ResponseLoggingFilter responseloggingfilter;
	
	@BeforeClass
	public void setUp() throws FileNotFoundException
	{
		RestAssured.baseURI	= Routes.BASE_URL;  // It will bydefault use base URL in test cases
		
		FileOutputStream fos = new FileOutputStream(".\\logs\\test_Log1.log");
		
		PrintStream log = new PrintStream(fos, true);
		
		requestloggingfilter = new RequestLoggingFilter(log);
		
		responseloggingfilter = new ResponseLoggingFilter(log);
		
		RestAssured.filters(requestloggingfilter, responseloggingfilter);
	}
	
	
	boolean isSortedDescending(List<Integer> list)
	{
		for(int i=0;i<list.size()-1;i++)
		{
			if(list.get(i)<list.get(i+1))
			{
				return false; //
			}
		}
		
		return true;
	}

	
	boolean isSortedAscending(List<Integer> list)
	{
		for(int i=0;i<list.size()-1;i++)
		{
			if(list.get(i)>list.get(i+1))
			{
				return false; //
			}
		}
		
		return true;
	}
}
