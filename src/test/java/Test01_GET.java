import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;


public class Test01_GET {

	@Test
	public void GET_Test_1() {
		Response response = get("https://reqres.in/api/users?page=2");
		//		System.out.println(response.asString()); // same like: response.getBody().asString()		
		System.out.println(response.prettyPrint()); // pretty JSON structure
		System.out.println(response.getStatusLine()); // HTTP/1.1 200 OK
		System.out.println(response.getHeader("content-type")); // application/json; charset=utf-8
		System.out.println(response.getTime()); // 4395

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	@Test
	public void GET_Test_2() {
		given()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("data.id[0]", equalTo(7));
	}

}
