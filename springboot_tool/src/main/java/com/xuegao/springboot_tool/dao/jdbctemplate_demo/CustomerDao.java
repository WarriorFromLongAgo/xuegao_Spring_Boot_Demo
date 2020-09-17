package com.xuegao.springboot_tool.dao.jdbctemplate_demo;

import com.alibaba.fastjson.JSON;
import com.xuegao.springboot_tool.model.doo.SysUserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.dao.jdbctemplate_demo
 * <br/> @ClassName：SysUserinfoDao
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
    public int insertSelective(SysUserinfo customer) {
        StringJoiner p = new StringJoiner(",", "(", ")");
        StringJoiner v = new StringJoiner(",", "(", ")");
        // Optional.ofNullable(customer.getSysUserinfoName()).ifPresent(x -> {
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
        //     if (null != customer.getSysUserinfoName()) {
        //         ps.setString(index++, customer.getSysUserinfoName());
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
    public SysUserinfo queryBySysUserinfoName(String customerName) {
        return jdbcTemplate.query("SELECT * FROM customer WHERE customer_name = ?",
                ps -> ps.setString(1, customerName), SINGLE);
    }

    public List<SysUserinfo> queryAll() {
        return jdbcTemplate.query("SELECT * FROM customer", MULTI);
    }

    public int updateByPrimaryKeySelective(SysUserinfo customer) {
        final long id = Objects.requireNonNull(Objects.requireNonNull(customer).getId());
        StringBuilder sql = new StringBuilder("UPDATE customer SET ");
        // Optional.ofNullable(customer.getSysUserinfoName()).ifPresent(x -> sql.append("customer_name = ?,"));
        // Optional.ofNullable(customer.getCreateTime()).ifPresent(x -> sql.append("create_time = ?,"));
        // Optional.ofNullable(customer.getEditTime()).ifPresent(x -> sql.append("edit_time = ?,"));
        // StringBuilder q = new StringBuilder(sql.substring(0, sql.lastIndexOf(","))).append(" WHERE id = ?");
        // return jdbcTemplate.update(q.toString(), ps -> {
        //     int index = 1;
        //     if (null != customer.getSysUserinfoName()) {
        //         ps.setString(index++, customer.getSysUserinfoName());
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

    private static SysUserinfo convert(ResultSet rs) throws SQLException {
        SysUserinfo customer = new SysUserinfo();
        customer.setId(rs.getLong("id"));
        customer.setUsername(rs.getString("customer_name"));
        // customer.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
        // customer.setCreateTime(rs.getTimestamp("edit_time").toLocalDateTime());
        return customer;
    }

    private static ResultSetExtractor<List<SysUserinfo>> MULTI = rs -> {
        List<SysUserinfo> result = new ArrayList<>();
        while (rs.next()) {
            result.add(convert(rs));
        }
        return result;
    };

    private static ResultSetExtractor<SysUserinfo> SINGLE = rs -> rs.next() ? convert(rs) : null;

    private void batchInsertByParams() {
        String sql = "INSERT INTO `money` (`name`, `money`, `is_deleted`) VALUES (?, ?, ?);";

        Object[] param1 = new Object[]{"Batch 一灰灰 3", 200, 0};
        Object[] param2 = new Object[]{"Batch 一灰灰 4", 200, 0};
        int[] ans = jdbcTemplate.batchUpdate(sql, Arrays.asList(param1, param2));
        System.out.println("batch insert by params: " + JSON.toJSONString(ans));
    }


    private void batchInsertByStatement() {
        String sql = "INSERT INTO `money` (`name`, `money`, `is_deleted`) VALUES (?, ?, ?);";

        int[] ans = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                if (i == 0) {
                    preparedStatement.setString(1, "batch 一灰灰5");
                } else {
                    preparedStatement.setString(1, "batch 一灰灰6");
                }
                preparedStatement.setInt(2, 300);
                byte b = 0;
                preparedStatement.setByte(3, b);
            }

            @Override
            public int getBatchSize() {
                return 2;
            }
        });
        System.out.println("batch insert by statement: " + JSON.toJSONString(ans));
    }
}