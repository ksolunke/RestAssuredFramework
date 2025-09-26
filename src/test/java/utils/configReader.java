package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader 
{
	Properties properties;

	String path = ".\\src\\test\\resources\\config.properties";
	
	public configReader() throws Exception
	{
		properties = new Properties();
		
		try(FileInputStream fileinputsteam = new FileInputStream(path))
		{
			properties.load(fileinputsteam);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			throw new Exception("File loading failed....");
		}
		
	}
	
	public String getProperty(String key)
	{
		return properties.getProperty(key);
	}
	
	public int getIntProperty(String key)
	{
		return Integer.parseInt(getProperty(key));
	}
	
}
