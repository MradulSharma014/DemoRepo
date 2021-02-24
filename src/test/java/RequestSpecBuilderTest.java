import files.Payload1;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestSpecBuilderTest {
    public static void main(String[] args) {
        //RestAssured.baseURI = "https://rahulshettyacademy.com";
        //Add Place
//        String response = given().log().all(). queryParam("key","qaclick123")
//                .header("Content-Type", "application/json")
//                .body(Payload1.addPlace())
//                .when().post("maps/api/place/add/json")
//                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
//                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

//        JsonPath jp = ReusableMethods.rawToString(response);
//        String placeIDVal = jp.get("place_id");
//        System.out.println(placeIDVal);

        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://rahulshettyacademy.com");
        p.setName("Rahul Shetty Academy");
        List<String> typesVal = new ArrayList<>();
        typesVal.add("shoe park");
        typesVal.add("shop");
        p.setTypes(typesVal);
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        p.setLocation(location);

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON)
                .build();

       // RequestSpecification res = given().spec(req).body(Payload1.addPlace());
        RequestSpecification res = given().spec(req).body(p);
        ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
                .build();

        Response resp = res.when().post("maps/api/place/add/json")
                .then().spec(respSpec).extract().response();

        String responseString = resp.asString();
        System.out.println(responseString);

    }
}