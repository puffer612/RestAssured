package com.youlu.step;

import com.youlu.config.LoginConfig;
import com.youlu.config.RedisConfig;
import com.youlu.util.ApiUtils;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.lang.StringUtils;

public class QBStepDefinitions {
    String token = "";
    String name= "";
    String testId = "";
    String result = "";
    /*
    * 当执行第一个场景才会执行下面的操作，所以这里面放置登录token
    * */
    @Before
    public void before(Scenario scenario) throws Throwable {
        LoginConfig.getToken();
        /*
        * 欠一个参数化的过程
        * */
    }

    @Given("name is {string} and testId is {string}")
    public void qb(String arg0,String arg1) {
        name = arg0;
        testId = arg1;
    }
    @And("token is {string}")
    public void stepAnd(String arg0){
        token = RedisConfig.getData("Token");
    }

    @When("method is {string}")
    public void get(String method) {
        Response response = ApiUtils.getResponse(name,testId,token,method);
        result = ApiUtils.getJsonPathValue(response,"result");
    }

    @Then("assert result={string}")
    public void assertResultValue(String value) {
        assert result.equals(value);
    }
}
