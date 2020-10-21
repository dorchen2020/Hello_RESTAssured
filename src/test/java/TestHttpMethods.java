import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;


public class TestHttpMethods {

	@Test (priority = 0)
	public void testGET() {
		given().
			header("Content-Type","application/json"). // can use also: param(parameterName, parameterValues)
			get("https://reqres.in/api/users?page=2").
		then().statusCode(200).
			body("data.id[2]",equalTo(9)).
			body("data.first_name", hasItems("Michael", "Lindsay")). 
			body("data.first_name", hasSize(6)). // count of first_name`s 
			log().all(); // print whole response 
	}
	
	@Test (priority = 1)
	public void testPOST() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "dor");
		map.put("job", "Automation");
		JSONObject request = new JSONObject(map);
		// or put key+value directly
		//		JSONObject request = new JSONObject();		
		//		request.put("name", "dor");
		//		request.put("job", "Automation");
		
		given().
			// parsing Json Type and get response also JSON type.
			header("Content-Type","application/json"). // similar to: contentType(ContentType.JSON)
			accept(ContentType.JSON). // shortcut for: header("Accept", contentType.JSON)
			body(request.toJSONString()).
		when().
			post("https://reqres.in/api/users").
		then().
			statusCode(201).log().body();
	}
	
	@Test (priority = 2)
	public void testPUT() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Max");
		map.put("job", "QA");
		JSONObject request = new JSONObject(map);
		
		given().
		header("Content-Type","application/json"). // similar to: contentType(ContentType.JSON)
			body(request.toJSONString()).
		when().
			put("https://reqres.in/api/users/2").
		then().
			statusCode(200).log().body();
	}
	
	@Test (priority = 3)
	public void testPATCH() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Max");
		map.put("job", "Developer");
		JSONObject request = new JSONObject(map);
		
		given().
		header("Content-Type","application/json"). // similar to: contentType(ContentType.JSON)
			body(request.toJSONString()).
		when().
			patch("https://reqres.in/api/users/2").
		then().
			statusCode(200).log().body();
	}
	
	@Test (priority = 4)
	public void testDELETE() {
		
		when(). // or given().
			delete("https://reqres.in/api/users/2").
		then().
			statusCode(204)
			.log().body();
	}
}
