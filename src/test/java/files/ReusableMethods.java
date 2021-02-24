package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
    public static JsonPath rawToString(String response){
        JsonPath jp = new JsonPath(response);
        return jp;
    }
}
