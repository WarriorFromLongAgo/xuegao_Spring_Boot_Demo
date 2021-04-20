package com.xuegao.xuegaomybatis.mybatis;

import com.xuegao.xuegaomybatis.config.PropertiesListenerConfig;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * <br/> @PackageName：com.xuegao.xuegaomybatis.mybatis
 * <br/> @ClassName：mysqlTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/04/18 11:34
 */
public class MysqlDriverTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String utl = "jdbc:mysql://localhost:3306/local?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = PropertiesListenerConfig.getProperties("jdbc.properties");
            connection = DriverManager.getConnection(utl, properties);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id, name, price, stocks, description FROM t_product");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}