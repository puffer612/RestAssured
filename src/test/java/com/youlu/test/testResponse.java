package com.youlu.test;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.youlu.config.JDBCConfig;
import com.youlu.config.LoginConfig;
import com.youlu.config.RedisConfig;
import com.youlu.util.ApiUtils;
import io.restassured.response.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class testResponse {
   /* @Test
    public void test1(){
        boolean a = RedisConfig.setData("Token","8A34A5B4803742A1A97988A45F205CB7");
        System.out.println(a);

    }*/

    @Test
    public void test2(){
/*        Response response = ApiUtils.getResponse("QB0001","0001","","post");
        Object obj = ApiUtils.getJsonPathObj(response,"data");
        System.out.println(StringUtils.strip(obj.toString(),"[]"));
        JSONArray json = JSONArray.fromObject(obj);*/
//        System.out.println(StringUtils.strip(json.toString(),"[]"));
//        String data = "{captchaToken=7CD3A9EA8C3A443EBF168B5F7E7E7BC0, captchaValue=4551}";
     /*   String data = "[{'captchaToken':'9123411003064B6C84EB1BA5702AA626','captchaValue':'2985'}]";
        JSONArray json = JSONArray.fromObject(data);
        System.out.println(JsonPath.parse("captchaToken").read(String.valueOf(json)).toString());*/

//        String data = "{\"captchaToken\":\"0E04359498B246DFB90E1582982EB434\",\"captchaValue\":\"5521\"}";
   /*     String data = "{\"captchaToken\":\"F3AD3004C54842D9A90ABEDFB8C11284\",\"captchaValue\":\"4165\"}";
        JDBCConfig.update(data);*/
//        String data = data1.replace('\"','\'');
       /* String sql ="update interface set params="+"\""+"{'userLoginpwd':'840428wtWT','captcha':"+data+",'userMobile':''" +
                ",'userLoginname':'YL037755','verify':'','userEmail':''}"+"\""+" where id=2";
        System.out.println(sql);*/
        LoginConfig.getToken();
//        String token_json = "{userId=USER20201116220000000061, token=BB6C05A5DBBB401D90665BB6F0CEDE5A}";
    /*    String token_json = "{token=BB6C05A5DBBB401D90665BB6F0CEDE5A}";
//        JSONObject jsonObject = JSONObject.fromObject(token_json);
        Map<String,Object> map = new HashMap<>();
        map = new Gson().fromJson(token_json,map.getClass());
        System.out.println(map.get("token").toString());*/
    }
}
