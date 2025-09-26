package utils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProviders {

	@DataProvider
	public Object[][] jsonDataProvider() throws IOException
	{
		//Path to JSON file
		
		String filepath = ".\\testdata\\product.json";
		
		// Read JSON file and map it to a list of Map
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<Map<String, String>> dataList = objectMapper.readValue(new File(filepath), new TypeReference<List<Map<String, String >>>(){});
		
		//Convert List<Map<String, String>> to Object[][]
		
		Object[][] dataArray = new Object[dataList.size()][];
		
		for(int i=0; i<dataList.size(); i++)
		{
			dataArray[i] = new Object[] { dataList.get(i)};
		}
		
		return dataArray;
	}

}
