package com.pjatk.MPR;

import com.pjatk.MPR.model.Cow;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class CowControllerAssuredTest {
    private static final String URI = "http://localhost:8080";
    @Test
    public void testGetCow(){
        when()
                .get(URI+"/cow/Krieg")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id",equalTo(1))
                .body("name",equalTo("Krieg"))
                .log()
                .body();
    }
//    @Test
//    public void testGetCow2(){
//        Cow cow =when()
//                .get(URI+"/cow/Krieg")
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(Cow.class);
//        assertEquals(1,cow.getId());
//        assertEquals("Krieg", cow.getName());
//    }
    ///newCow
    @Test
    public void testPost(){
        with()
                .body(new Cow(1L,"Krieg",6))
                .contentType("application/json")
                .post(URI+"/cowNew")
                .then()
                .assertThat()
                .body("id",equalTo(1))
                .body("name",equalTo("Krieg"))
                .body("age",equalTo(6))
                .statusCode(200)
                .log()
                .body();
    }
}
