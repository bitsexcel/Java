package WebApiAutomation.WebApiAutomation;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
//import junit.framework.Assert;

public class Get404 extends BaseClass{

	
		
	@BeforeMethod
	public void setup()
	{
		client = HttpClientBuilder.create().build();
	}
	
	@AfterMethod
	public void closeResources() throws IOException
	{
		client.close();
		response.close();
	}
	
	@Test
	public void nonExistingUrlReturns404() throws IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexisturl");
		response = client.execute(get);
		int actualStatus = response.getStatusLine().getStatusCode();
		Reporter.log("status: " + actualStatus, true);
		Assert.assertEquals(actualStatus, 404);
	}
	


}
