
package com.accountapi.testCases;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.accountapi.base.TestBase;
import com.accountapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Account_Record extends TestBase{

	RequestSpecification httpRequest;
	Response response;
	
	String accountHolderName=RestUtils.accountName();
	String accountNum=RestUtils.accountNum();
	String accountType=RestUtils.accountType();
	String id=RestUtils.id();
	String swiftCode=RestUtils.swiftCode();

	
	@BeforeClass
	void createAccount() throws InterruptedException
	{
		logger.info("*********Started TC003_Post_Account_Record **********");
		

	    RestAssured.baseURI = "https://ig.aidtaas.com/market-place/v1.0/accounts";
	    httpsRequest = RestAssured.given();  // Change from httpsRequest to httpRequest

	    // JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
	  //  HashMap<String, Object> requestParams = new HashMap<>();
	    JSONObject requestParams = new JSONObject();

	    requestParams.put("accountHolderName", "QA_ACCOUNT");
        requestParams.put("accountNum", accountNum);
	    requestParams.put("accountType", "Company");
	    requestParams.put("active", "true");
	    requestParams.put("id", id);
	    requestParams.put("swiftCode","string");


	    // Add a header stating the Request body is a JSON
	    httpsRequest.header("Content-Type", "application/json");
	    // Add the Json to the body of the request
	    httpsRequest.body(requestParams.toString());

	    System.out.println("Request Payload: " + requestParams.toString());


	    response = httpsRequest.request(Method.POST);

	    Thread.sleep(5000);
	}

	
	
	@Test
	void checkResposeBody()
	{
		String responseBody = response.getBody().asString();

		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains(id), true);
//		Assert.assertEquals(responseBody.contains(), true);
//		Assert.assertEquals(responseBody.contains(), true);
	}
		
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
		
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		//Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkserverType()
	{
		String serverType = response.header("Server");
		//Assert.assertEquals(serverType, "nginx/1.14.1");
	}

	@Test
	void checkcontentEncoding()
	{
		String contentEncoding = response.header("Content-Encoding");
		//Assert.assertEquals(contentEncoding, "gzip");

	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC003_Post_Account_Record **********");
	}

}
