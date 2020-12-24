package com.youlu.config;

import com.google.gson.Gson;
import com.youlu.util.ApiUtils;
import io.restassured.response.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginConfig {
    public static void getToken(){
           /*RedisConfig.setData("Token","8A34A5B4803742A1A97988A45F205CB7");*/
       Response response = ApiUtils.getResponse("QB0001","0001","","post");
        Object obj = ApiUtils.getJsonPathObj(response,"data");
        JSONArray json = JSONArray.fromObject(obj);
        String data = StringUtils.strip(json.toString(),"[]");
        JDBCConfig.update(data);
        response = ApiUtils.getResponse("QB0002","0001","","post");
        String token_json = ApiUtils.getJsonPathValue(response,"data");
        System.out.println(token_json);
        Map<String,Object> map = new HashMap<>();
        map = new Gson().fromJson(token_json,map.getClass());
        RedisConfig.setData("TOKEN",map.get("token").toString());
    }
}
