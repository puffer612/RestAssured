package com.youlu;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class StepDefinitions {
//    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    int num1 = 0;
    int num2 = 0;
    int result = 0;
    @Given("{int} {int}")
    public void step1(int a,int b){
        num1=a;
        num2=b;
    }
    @When("add")
    public void step2(){
    result = num1+num2;
    }
    @Then("assert {int}")
    public void step3(int c){
        assert result == c;
//        logger.info(String.valueOf(result));
    }

}
