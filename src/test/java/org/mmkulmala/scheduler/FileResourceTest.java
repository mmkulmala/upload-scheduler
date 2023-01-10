package org.mmkulmala.scheduler;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FileResourceTest {
    @Test
    public void testFileEndpoint() {
        given()
                .when().get("/file/test-file.txt")
                .then()
                .statusCode(200);
    }
}
