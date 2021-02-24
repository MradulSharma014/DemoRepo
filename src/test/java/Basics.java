import files.Payload1;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //Add Place
        String response = given().log().all(). queryParam("key","qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload1.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        JsonPath jp = ReusableMethods.rawToString(response);
        String placeIDVal = jp.get("place_id");
        System.out.println(placeIDVal);
System.out.println("placeIDVal");
System.out.println("placeIDVal");
System.out.println("placeIDVal");

        //Update Place
        String addressVal = "70 Summer walk, USA";
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"place_id\": \""+placeIDVal+"\",\n" +
                        "    \"address\": \""+addressVal+"\",\n" +
                        "    \"key\": \"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //Get Place
        String getPlaceResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeIDVal)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).body("address",equalTo( addressVal)).extract().asString();
        JsonPath jp1 = ReusableMethods.rawToString(getPlaceResponse);
        String actualAddress = jp1.get("address");
        System.out.println(actualAddress);
        Assert.assertEquals(addressVal, actualAddress);

    }
}