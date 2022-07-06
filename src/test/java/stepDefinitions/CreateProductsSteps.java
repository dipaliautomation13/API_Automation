package stepDefinitions;

import com.google.inject.Inject;
import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import org.junit.
import junit.framework.Assert;
import util.Common;
import util.Configuration;
import util.EndPoints;

import java.io.IOException;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class CreateProductsSteps {

    @Inject
    private Configuration config;
    @Inject
    private EndPoints endPoints;
    @Inject
    private Common common;

     Response resp;
     RequestSpecification requestSpec = config.getRequestSpecification();
     DocumentContext documentContext;

    public String productName;
    public String productId;
    public int productPrice;
    public int productItemCount;

    @Given("^request body with valid ([^\"]*) \"(\\\\d+)\" \"(\\\\d+)\" $")


    public void requestBodyWithValidName(String product, int price, int itemCount)throws IOException {
        productName=product;
        productPrice = price;
        productItemCount = productItemCount;
        documentContext = common.updateJson("//src//test//resources//Templates//createProduct.json");
        documentContext.set("$.name", productName);
        documentContext.set("$.price", productPrice);
        documentContext.set("$.itemCount", productItemCount);
    }

    @When("I do post call to create the product")
    public void iDoPostCallToCreateTheProduct() {
        resp = given().spec(requestSpec).body(documentContext.jsonString()).post(endPoints.get_product);
    }

    @Then("I validate response code for successful")
    public void iValidateResponseCodeForSuccessful() {
        resp.then().statusCode(200).log().all();
    }

    @And("I validate response message of product created")
    public void iValidateResponseMessageOfProductCreated() {
        Assert.assertEquals(resp.body().jsonPath().get("name"),productName);
        productId = resp.body().jsonPath().get("id");
    }

    @And("I validate that the product is created in DB successfully")
    public void iValidateThatTheProductIsCreatedInDBSuccessfully() {
        resp = given().spec(requestSpec).get(endPoints.baseUrl+endPoints.get_product+"/"+productId);
        resp.then().statusCode(200).log().all();
        Assert.assertEquals(resp.body().jsonPath().get("name"),productName);
        Assert.assertEquals(Optional.ofNullable(resp.body().jsonPath().get("price")),productPrice);
        Assert.assertEquals(Optional.ofNullable(resp.body().jsonPath().get("itemCount")),productItemCount);
        Assert.assertNotNull(resp.body().jsonPath().get("created"));
        Assert.assertNotNull(resp.body().jsonPath().get("modified"));

    }

}