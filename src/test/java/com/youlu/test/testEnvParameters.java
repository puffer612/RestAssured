package com.youlu.test;

import org.junit.Test;

public class testEnvParameters {
    @Test
    public void test1(){
        System.out.println(System.getenv("ENV_CODE"));
    }
}
