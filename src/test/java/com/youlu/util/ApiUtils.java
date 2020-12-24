package com.youlu.util;

import com.youlu.config.JDBCConfig;
import com.youlu.config.YamlConfig;
import io.restassured.response.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {
    //日志记录
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
    * 数据封装,返回一个map集合
    * */
    public static Map<String,Object> getParams(String name,String testId,String token){
        Map<String,Object> map = JDBCConfig.getData(name,testId);
        Map<String,Object> params = new HashMap<>();
        if(map.size()>0){
            Map<String,Object> apis = new HashMap<>();
            apis.put("api",map.get("api"));
            apis.put("cid",map.get("cid"));
            if(!map.get("params").equals("")){
                apis.put("params", JSONObject.fromObject(map.get("params")));
            }
            // 因为apis这个数据需要json数组，这个地方把字符串转化为json数组
            JSONArray json = JSONArray.fromObject(apis);
            params.put("apis",json);
            params.put("ctype",map.get("ctype"));
            params.put("TOKEN",token);
        }
        return params;
    }

    /*
    * 获取返回信息
    * @param name 用例编号
    * @param testId 测试数据编号
    * @param token 用户token
    * @param method 请求方式
    * @return
    * */
    public static Response getResponse(String name,String testId,String token,String method){
        Response response = null;
        String url = YamlConfig.getEnvConfig().get("test");
        Map<String,Object> params = getParams(name,testId,token);
        if(method.equals("get")){
             response = given().contentType("application/json;charset=UTF-8")
                    .formParam("apis",params.get("apis").toString())
                    .formParam("ctype",params.get("ctype").toString())
                    .formParam("TOKEN",params.get("TOKEN").toString())
                    .when().log().all().get(url);
        }else if(method.equals("post")){
            response= given().contentType("application/json;charset=UTF-8")
                    .header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8")
                    .formParam("apis", params.get("apis").toString())
                    .formParam("ctype",params.get("ctype").toString())
                    .when().log().all().post(url);
        }

        return response;
    }

    /*
    * 用response 解析
    * @param response 接口返回数据
    * @param jsonPath jsonPath, 例如 a.b.c   a.b[1].c  a
    * */
    public static String getJsonPathValue(Response response,String jsonPath){
        Object obj = response.jsonPath().get(jsonPath);
        return StringUtils.strip(String.valueOf(obj),"[]");
    }

    public static Object getJsonPathObj(Response response,String jsonPath){
        return response.jsonPath().get(jsonPath);
    }
}
