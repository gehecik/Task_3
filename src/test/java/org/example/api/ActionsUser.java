package org.example.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class ActionsUser {
    @Step("Delete user by accessToken")
    public Response deleteByToken(String accessToken) {
        return given()
                .header("authorization", accessToken)
                .when()
                .delete("/api/auth/user");

    }

    @Step("Create user")
    public Response createUser(Object user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/auth/register");
    }

    @Step("Create user and get accessToken")
    public String createUserGetToken(Object user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/auth/register")
                .then()
                .extract()
                .path("accessToken");
    }

    @Step("Login user")
    public Response loginUser(Object user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/auth/login");
    }
}
