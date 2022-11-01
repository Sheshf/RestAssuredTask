package test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class Task2google {

	@Test
	public void test1() {
		
		baseURI = "https://www.google.com/recaptcha/api2/";
		
		given().
			header("Content-Type", "application/json").
		when().
			post("webworker.js?hl=en&v=ovmhLiigaw4D9ujHYlHcKKhP").
		then().
			statusCode(500).log().all().extract().response();

		
	}
}
