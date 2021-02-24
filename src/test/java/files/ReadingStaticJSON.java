package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class ReadingStaticJSON {
    @Test
    public void staticAddBookFlow() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("E:\\Udemy\\Rest api-automation-testing-rest-assured\\Section 7 Handling Dynamic Json Payloads with Parameterization\\staticJson.json"))))
                        .when().post("Library/Addbook.php")
                .then().log().all().extract().response().asString();

        JsonPath jp = ReusableMethods.rawToString(response);
        String idVal = jp.getString("ID");
        System.out.println(idVal);
    }
}
