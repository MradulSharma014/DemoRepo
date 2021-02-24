import files.Payload1;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJSONParse {
    //Stub Method
    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload1.coursePrice());

        //Print No. of courses returned by api
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print purchase amount from api
        int purchaseAmt = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmt);

        //Print Title of first course
        String course1 = js.get("courses[0].title");
        System.out.println(course1);

        //Print All course titles and their respective Prices
        for (int i = 0; i < count; i++)
        {
            String courseName = js.get("courses["+i+"].title");
            System.out.println(courseName);
            int coursePrice = js.get("courses["+i+"].price");
            System.out.println(coursePrice);
        }

        //Print no of copies sold by RPA Course
        for (int i = 0; i < count; i++)
        {
            if(js.get("courses["+i+"].title").equals("RPA")) {
                int courseCopy = js.get("courses["+i+"].copies");
                System.out.println(courseCopy);
                break;
            }
        }

        //Verify if Sum of all Course prices matches with Purchase Amount
        int purchaseAmt1 = js.getInt("dashboard.purchaseAmount");
        int sum = 0;
        for (int i = 0; i < count; i++)
        {
            int courseCopy = js.get("courses["+i+"].copies");
            int coursePrice = js.get("courses["+i+"].price");
            sum = sum+(courseCopy * coursePrice);
           //System.out.println("====");
            //System.out.println(sum);
        }
        System.out.println(purchaseAmt1);
        System.out.println(sum);
        if(purchaseAmt1 == sum)
        {
            System.out.println("True");
        }
        else{
            System.out.println("False");
        }
        Assert.assertEquals(purchaseAmt1, sum);
    }
}
