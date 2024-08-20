import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class PostRequest {
    public static void main(String[] args) {
        String url="https://petstore.swagger.io/v2/user";
        Map<String, Object> header=new HashMap<>();
        header.put("Accept", "application/json");
        header.put("Content-Type", "application/json");

        String requestBody = "{\n" +
                "  \"id\": 123456789,\n" +
                "  \"username\": \"ozent\",\n" +
                "  \"firstName\": \"özen\",\n" +
                "  \"lastName\": \"taştan\",\n" +
                "  \"email\": \"ozentastan1@gmail.com\",\n" +
                "  \"password\": \"123456789..\",\n" +
                "  \"phone\": \"05453252910\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        String contentType = ContentType.JSON.toString();

        Response response = RestAssured.given()
                .contentType(contentType)
                .headers(header)
                .body(requestBody)
                .when().log().all()
                .post(url);

        //Execute sonrası dönen response body--> code:200, type:unknown, message:123456789
        response.then().log().all();
                /*.statusCode(200).body("code",equalTo("200"))
                .body("type",equalTo("unknown"))
                .body("message",equalTo("123456789"));*/
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body().jsonPath().getString("type")).isEqualTo("unknown");
        assertThat(response.body().jsonPath().getString("message")).isEqualTo("123456789");
    }

}
