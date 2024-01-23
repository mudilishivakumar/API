
package com.accountapi.testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.accountapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Account_Record extends TestBase{

	RequestSpecification httpRequest;
	Response response;
		
	@BeforeClass
	void getAccountData() throws InterruptedException
	{
		logger.info("*********Started TC002_Get_Single_Account_Record **********");
		
		RestAssured.baseURI = "https://ig.aidtaas.com/market-place/v1.0";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/accounts/"+id);
		
		Thread.sleep(7000);
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
	void checkResponseTime()
	{
		long responseTime = response.getTime(); // Getting status Line
		Assert.assertTrue(responseTime<6000);
		
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
		//.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkserverType()
	{
		String serverType = response.header("Server");
		//Assert.assertEquals(serverType, "nginx/1.14.1");
	}

	@Test
	void checkContentLenght()
	{
		String contentLength = response.header("Content-Length");
		//Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC002_Get_Single_account_Record **********");
	}
	
}
