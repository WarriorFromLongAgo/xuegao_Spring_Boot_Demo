package com.xuegao.springboot_tool.dao.jdbctemplate_demo;

import com.xuegao.springboot_tool.model.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.dao.jdbctemplate_demo
 * <br/> @ClassName：UserInfoDao
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/20 9:43
 */
@Repository
public class CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 增
     */
    public int insertSelective(UserInfo customer) {
        StringJoiner p = new StringJoiner(",", "(", ")");
        StringJoiner v = new StringJoiner(",", "(", ")");
        // Optional.ofNullable(customer.getUserInfoName()).ifPresent(x -> {
        //     p.add("customer_name");
        //     v.add("?");
        // });
        // Optional.ofNullable(customer.getCreateTime()).ifPresent(x -> {
        //     p.add("create_time");
        //     v.add("?");
        // });
        // Optional.ofNullable(customer.getEditTime()).ifPresent(x -> {
        //     p.add("edit_time");
        //     v.add("?");
        // });
        // String sql = "INSERT INTO customer" + p.toString() + " VALUES " + v.toString();
        // KeyHolder keyHolder = new GeneratedKeyHolder();
        // int updateCount = jdbcTemplate.update(con -> {
        //     PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //     int index = 1;
        //     if (null != customer.getUserInfoName()) {
        //         ps.setString(index++, customer.getUserInfoName());
        //     }
        //     if (null != customer.getCreateTime()) {
        //         ps.setTimestamp(index++, Timestamp.valueOf(customer.getCreateTime()));
        //     }
        //     if (null != customer.getEditTime()) {
        //         ps.setTimestamp(index, Timestamp.valueOf(customer.getEditTime()));
        //     }
        //     return ps;
        // }, keyHolder);
        // customer.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return 1;
    }

    /**
     * 删
     */
    public int delete(long id) {
        return jdbcTemplate.update("DELETE FROM customer WHERE id = ?", id);
    }

    /**
     * 查
     */
    public UserInfo queryByUserInfoName(String customerName) {
        return jdbcTemplate.query("SELECT * FROM customer WHERE customer_name = ?",
                ps -> ps.setString(1, customerName), SINGLE);
    }

    public List<UserInfo> queryAll() {
        return jdbcTemplate.query("SELECT * FROM customer", MULTI);
    }

    public int updateByPrimaryKeySelective(UserInfo customer) {
        final long id = Objects.requireNonNull(Objects.requireNonNull(customer).getId());
        StringBuilder sql = new StringBuilder("UPDATE customer SET ");
        // Optional.ofNullable(customer.getUserInfoName()).ifPresent(x -> sql.append("customer_name = ?,"));
        // Optional.ofNullable(customer.getCreateTime()).ifPresent(x -> sql.append("create_time = ?,"));
        // Optional.ofNullable(customer.getEditTime()).ifPresent(x -> sql.append("edit_time = ?,"));
        // StringBuilder q = new StringBuilder(sql.substring(0, sql.lastIndexOf(","))).append(" WHERE id = ?");
        // return jdbcTemplate.update(q.toString(), ps -> {
        //     int index = 1;
        //     if (null != customer.getUserInfoName()) {
        //         ps.setString(index++, customer.getUserInfoName());
        //     }
        //     if (null != customer.getCreateTime()) {
        //         ps.setTimestamp(index++, Timestamp.valueOf(customer.getCreateTime()));
        //     }
        //     if (null != customer.getEditTime()) {
        //         ps.setTimestamp(index++, Timestamp.valueOf(customer.getEditTime()));
        //     }
        //     ps.setLong(index, id);
        // });
        return 1;
    }

    private static UserInfo convert(ResultSet rs) throws SQLException {
        UserInfo customer = new UserInfo();
        customer.setId(rs.getLong("id"));
        customer.setUsername(rs.getString("customer_name"));
        // customer.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
        // customer.setCreateTime(rs.getTimestamp("edit_time").toLocalDateTime());
        return customer;
    }

    private static ResultSetExtractor<List<UserInfo>> MULTI = rs -> {
        List<UserInfo> result = new ArrayList<>();
        while (rs.next()) {
            result.add(convert(rs));
        }
        return result;
    };

    private static ResultSetExtractor<UserInfo> SINGLE = rs -> rs.next() ? convert(rs) : null;
}