package com.youlu.config;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JDBCConfig {
    //这个工具类,主要为我们获取一个数据库连接
    private static String driverName = YamlConfig.getMysqlConfig().get("driverName");
   private static String url = YamlConfig.getMysqlConfig().get("url");
   private static String username = YamlConfig.getMysqlConfig().get("username");
   private static String password = YamlConfig.getMysqlConfig().get("password");

    //静态代码块,目的,让第一次使用到JDBCConfig中加载驱动,第二次以后不再加载了
    static{
        //1.加载驱动
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            //System.out.println("驱动加载失败..请检查驱动包");
            throw new RuntimeException("驱动加载失败..请检查驱动包");
        }
    }
    /*
    * 获取数据库的连接
    * */
    public static Connection getConnection() throws Exception{
        //2.获取和数据库的连接
        Connection conn =  DriverManager.getConnection(url, username, password);
        //3.返回连接对象
        return conn;

    }


    /*
    * 关闭所有资源
    * Connection  数据库连接
    * Statement 通过连接对象，获取sql执行对象
    * ResultSet 执行结果
    * */
    public static void closeAll(Connection conn, Statement st, ResultSet rs){
        //负责关闭
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void closeUpdate(Connection conn, Statement st){
        //负责关闭
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*
    * 查询结果并返回一个map集合
    * Connection  数据库连接
    * Statement 通过连接对象，获取sql执行对象
    * ResultSet 执行结果
    * */
    public static Map<String,Object> getData(String name,String testId){
        Map<String,Object> map = new HashMap<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            statement = conn.createStatement();
            String sql = "select api,ctype,params,cid from interface where name="+ "\""+name+"\""+"and testId="+"\""+testId+"\"";
            resultSet = statement.executeQuery(sql);
            //处理结果集
            while (resultSet.next()){
                map.put("api",resultSet.getObject("api"));
                map.put("ctype",resultSet.getObject("ctype"));
                map.put("params",resultSet.getObject("params"));
                map.put("cid",resultSet.getObject("cid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,statement,resultSet);
        }
        return map;
    }

    /*
    * Connection  数据库连接
    * Statement 通过连接对象，获取sql执行对象
    * */
    public static void update(String data){
        Connection conn = null;
        Statement statement = null;
        try {
            conn = getConnection();
            statement = conn.createStatement();
            String sql ="update interface set params="+"\""+"{'userLoginpwd':'840428wtWT','captcha':"+data.replace('\"','\'')+",'userMobile':'','userLoginname':'YL037755','verify':'','userEmail':''}"+"\""+" where id=2";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeUpdate(conn,statement);
        }
    }
}
