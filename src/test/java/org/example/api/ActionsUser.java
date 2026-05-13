package org.example.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class DeleteUser {
    @Step("Delete user by accessToken")
    public Response deleteByToken(String accessToken) {
        return given()
                .log().all()
                .header("authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .log().all()
                .extract()
                .response();
    }
}
