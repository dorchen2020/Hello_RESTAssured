import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class DataDriven extends DataForTests{
	
	String baseURI = "http://localhost:3000"; 
	
	@Test (dataProvider = "PostData", priority=1)
	public void testPOST(String firstName, String lastName, int age, int subjectId) { 

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName",firstName);
		map.put("lastName",lastName);
		map.put("age",age);
		map.put("subjectId",subjectId);
		JSONObject request = new JSONObject(map); 

		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post(baseURI+"/users").
		then().
			statusCode(201).
			log().all();
	}
	
	@Test (dataProvider = "PatchData", priority=2)
	public void testPATCH(String firstName, int age) {  

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName",firstName);
		map.put("age",age);
		JSONObject request = new JSONObject(map);	
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON). 
			body(request.toJSONString()).
		when().
			patch(baseURI+"/users/4").
		then().
			statusCode(200).
			log().all();
	}
	
	@Test (dataProvider = "PutData", priority=3)
	public void testPUT(int userId, String firstName, String lastName, int age) { 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName",firstName);
		map.put("lastName",lastName);
		map.put("age",age);
		JSONObject request = new JSONObject(map);	
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON). 
			body(request.toJSONString()).
		when().
			put(baseURI+"/users/"+userId).
		then().
			statusCode(200).
			log().all();
	}
	
	@Test (dataProvider = "DeleteData", priority=4)
	public void testDELETE(int userId) {
		
		when().
			delete(baseURI+"/users/"+userId).
		then().
			statusCode(200);
	}
}
