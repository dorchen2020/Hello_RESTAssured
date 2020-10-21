import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;

public class HttpMethodsJsonServer {

	// get subject when name equals automation.
	@Test (priority=1)
	public void testGET() { 
		baseURI = "http://localhost:3000/"; 
		given().
			param("name", "Automation").
			get("/subjects").
		then().
			statusCode(200).
			log().all();
	}
	
	// add user (fourth user)
	@Test (priority=2)
	public void testPOST() { 
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName","Tom");
		map.put("lastName","Cooper");
		map.put("age","40");
		map.put("subjectId","1");
		JSONObject request = new JSONObject(map);
		
		baseURI = "http://localhost:3000/"; 
		
		given().
			// parsing Json Type and get response also JSON type.
			contentType(ContentType.JSON). // similar to: header("Content-Type","application/json") 
			accept(ContentType.JSON). // shortcut for: header("Accept", contentType.JSON) 
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
	
	// update firstName and subjectId for fourth user.
	@Test (priority=3)
	public void testPATCH() {
		
		baseURI = "http://localhost:3000/"; 

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName","Alon");
		map.put("subjectId","2");
		JSONObject request = new JSONObject(map);
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON). 
			body(request.toJSONString()).
		when().
			patch("/users/4").
		then().
			statusCode(200).
			log().all();
	}

	// update whole user
	@Test (priority=4)
	public void testPUT() {
		
		baseURI = "http://localhost:3000/"; 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName","Chen");
		map.put("lastName","Navon");
		map.put("age","20");
		map.put("subjectId","2");
		JSONObject request = new JSONObject(map);
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON). 
			body(request.toJSONString()).
		when().
			put("/users/4").
		then().
			statusCode(200).
			log().all();
	}
	
	// delete forth user
	@Test (priority=5)
	public void testDELETE() {
		
		baseURI = "http://localhost:3000/"; 
		when(). // or given().
			delete("/users/4").
		then().
			statusCode(200)
			.log().body();
	}
}
