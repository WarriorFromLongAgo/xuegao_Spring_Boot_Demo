package com.xuegao.springboot_tool.constant.consist;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.constant.consist
 * <br/> @ClassName：Constant
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/14 10:58
 */
public class Constant {

    public interface managerr {
        String PERMISSIONS_KEY = "permissions-key";
        String USERID_KEY = "userid-key";
        String USERNAME_KEY = "username-key";
        String ROLES_KEY = "roles-key";
        String DEPT_TYPE = "D";
        Integer DATA_NOT_DELETED = 1;
        String DB_TYPE_MYSQL = "mysql";
        String DB_TYPE_ORACLE = "oracle";
        String DB_TYPE_SQL_SERVER = "sqlServer";
        String ACCESS_TOKEN = "authorization";
    }

    public interface RedisConstant {

        String OK = "OK";
        // 过期时间, 60s, 一分钟
        Integer EXPIRE_TIME_MINUTE = 60;
        // 过期时间, 600s, 十分钟
        Integer EXPIRE_TIME_TEN_MINUTE = 60 * 10;
        // 过期时间, 一小时
        Integer EXPIRE_TIME_HOUR = 60 * 60;
        // 过期时间, 半天
        Integer EXPIRE_TIME_HALF_DAY = 60 * 60 * 12;
        // 过期时间, 一天
        Integer EXPIRE_TIME_DAY = 60 * 60 * 24;
        // token 头
        String TOKEN_PREFIX = "token:";
        // 消息消费者前缀
        String MESSAGE_CONSUMER_PREFIX = "consumer:";
        // 访问限制前缀
        String ACCESS_LIMIT_PREFIX = "accessLimit:";
    }

    public interface LogType {
        Integer LOGIN = 1;// 登录
        Integer LOGOUT = 2;// 登出
    }

    public interface MessageLogStatus {
        Integer DELIVERING = 0;// 消息投递中
        Integer DELIVER_SUCCESS = 1;// 投递成功
        Integer DELIVER_FAIL = 2;// 投递失败
        Integer CONSUMED_SUCCESS = 3;// 已消费
    }
}
