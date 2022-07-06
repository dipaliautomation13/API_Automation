package util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Configuration {

//    public void configure() {
//
//        RestAssured.baseURI = "https://schqarecruitment.azurewebsites.net/v1";
//        RestAssured.basePath = "/";
//    }

    public RequestSpecification getRequestSpecification() {

        return RestAssured.given().header("Accept","application/json").
                header("Content-Type","application/json");

    }
}
