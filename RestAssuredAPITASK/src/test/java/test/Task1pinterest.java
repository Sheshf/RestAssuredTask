package test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import java.time.Instant;
import java.util.Calendar;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class Task1pinterest {

	@Test
	public void test1() {
		JSONObject request2 = new JSONObject();

		long currenttime = Instant.now().getEpochSecond();		  
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currenttime*1000);
		calendar.add(Calendar.MILLISECOND, 86400000);
		long Nextdaytime = calendar.getTimeInMillis();	    

		baseURI = "https://ct.pinterest.com/user/";

		given().
		header("Content-Type", "application/json").
		contentType(ContentType.JSON).
		pathParam("currenttime", currenttime).pathParam("Nextdaytime", Nextdaytime).
		accept(ContentType.JSON).
		body(request2.toJSONString()).
		
		when().
		get("?event=search&ed=%7B%22np%22%3A%22gtm%22%7D&tid={currenttime}&cb={Nextdaytime}").
		
		then().
		log().all()
		.assertThat().statusCode(200) //Validate it returns status code 200
		.body("isUtilizingAdvertiser1pEnabled", Matchers.equalTo(false)) //Validate isUtilizingAdvertiser1pEnabled is false
		.body(JsonSchemaValidator.
				matchesJsonSchemaInClasspath("Jsonschema.json")) //validate the schema is correct. 
		.extract().response();
	}
}
