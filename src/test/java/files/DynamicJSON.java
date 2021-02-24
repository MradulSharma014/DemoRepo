package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJSON {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbnVal, String aisleVal)
    {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(Payload1.addBook(isbnVal, aisleVal))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

       JsonPath jp =  ReusableMethods.rawToString(response);
       String courseID = jp.get("ID");
       System.out.println(courseID);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData()
    {
        //Multidimentional array: Collection of arrays
       return new Object[][] {{"adfsgsa", "12eeqwedwq"},{"adfsgsae13", "12eeqwedwq12213"},{"adfsgsaasdqw", "12eeqwedwq3213"}};
    }
}
