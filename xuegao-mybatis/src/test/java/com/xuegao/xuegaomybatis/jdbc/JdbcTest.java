package com.xuegao.xuegaomybatis.jdbc;

import com.xuegao.xuegaomybatis.domain.Product;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.xuegaomybatis.jdbc
 * <br/> @ClassName：JdbcTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/04/18 12:07
 */
public class JdbcTest {

    @Test
    public void testJdbc() {
        String url = "jdbc:mysql://localhost:3306/myblog?user=root&password=1234&useUnicode=true&characterEncoding=UTF8&useSSL=false";

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url);

            String sql = "SELECT id, name, price, stocks, description FROM t_product";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Product> articles = new ArrayList<>(rs.getRow());
            while (rs.next()) {
                Product article = new Product();
                article.setId(rs.getLong("id"));
                article.setName(rs.getString("title"));
                article.setPrice(rs.getString("author"));
                article.setStocks(rs.getInt("author"));
                article.setDescription(rs.getString("content"));
                articles.add(article);
            }
            System.out.println("Query SQL ==> " + sql);
            System.out.println("Query Result: ");
            articles.forEach(System.out::println);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}