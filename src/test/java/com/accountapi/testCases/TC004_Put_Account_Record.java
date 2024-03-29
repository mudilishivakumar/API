

package com.accountapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.accountapi.base.TestBase;
import com.accountapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Account_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;
	String accountHolderName=RestUtils.accountName();
	String accountNum=RestUtils.accountNum();
	String accountType=RestUtils.accountType();
	String id=RestUtils.id();
	String swiftCode=RestUtils.swiftCode();
	
	@BeforeClass
	void updateEmployee() throws InterruptedException
	{
		logger.info("*********Started TC004_Put_Accound_Record **********");
		
		RestAssured.baseURI = "https://ig.aidtaas.com/market-place";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
		//{"name":"John123X","salary":"123","age":"23"}
		JSONObject requestParams = new JSONObject();
		requestParams.put("accountNum", accountNum);
		requestParams.put("accountType", accountType);
		requestParams.put("active", "true");
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.PUT, "//v1.0/accounts/"+id);
		
		Thread.sleep(5000);

	}
	
	@Test
	void checkResposeBody()
	{
		String responseBody = response.getBody().asString();
				
		Assert.assertEquals(responseBody.contains(id), true);
	
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
	//	Assert.assertEquals(serverType, "nginx/1.14.1");
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
		logger.info("*********  Finished TC004_Put_Account_Record **********");
	}

}
