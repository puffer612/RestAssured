package com.youlu.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class YamlConfig {
    /*
    * 获取yaml配置文件，返回一个map集合，用了自取
    * */
    public static Map<String,Object> getYamlData(){
        Map<String,Object> map = new HashMap<>();
        try {
            Yaml yaml = new Yaml();
            URL url = YamlConfig.class.getClassLoader().getResource("yaml/application.yml");
            if(url!=null){
                map = yaml.load(new FileInputStream(url.getFile()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }
    /*
    * 获取mysql返回信息
    * */
    public static Map<String,String> getMysqlConfig(){
        Map<String,String> data = new HashMap<>();
        Map<String,Object> map = getYamlData();
        try {
            if(map!=null){
                data = (Map<String, String>) map.get("mysql");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /*
    * 获取环境返回信息
    * */
    public static Map<String,String> getEnvConfig(){
        Map<String,String> data = new HashMap<>();
        Map<String,Object> map = getYamlData();
        try {
            if(map!=null){
                data = (Map<String, String>) map.get("api");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /*
    * 获取redis 配置信息
    * */
    public static Map<String,Object> getRedisConfig(){
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> map = getYamlData();
        try {
            if(map!=null){
                data = (Map<String, Object>) map.get("redis");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
