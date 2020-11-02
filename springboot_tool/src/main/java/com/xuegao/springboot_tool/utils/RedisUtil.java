
package com.xuegao.springboot_tool.utils;

import com.xuegao.springboot_tool.mvc.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis使用工具类.
 * <p>
 * 使用{@link RedisUtilConfig}注入redisTemplate才可使用
 * </p>
 * @author 80003093/tanquanfang
 */
@Slf4j
public class RedisUtil {
    private static final RedisUtil instance = new RedisUtil();
    private static RedisTemplate<String, Object> redisTemplate;
    private static StringRedisTemplate stringRedisTemplate;

    private RedisUtil() {
    }

    public static RedisUtil getInstance() {
        return instance;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        if (RedisUtil.redisTemplate == null) {
            RedisUtil.redisTemplate = redisTemplate;
        }
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        if (RedisUtil.stringRedisTemplate == null) {
            RedisUtil.stringRedisTemplate = stringRedisTemplate;
        }
    }

    private static RedisTemplate<String, Object> getRedisTemplate() {
        if (redisTemplate == null) {
            throw new BusinessException("请自行设置redisTemplate或扫描使用RedisUtilConfig配置后，才可正常使用");
        }
        return redisTemplate;
    }

    private static StringRedisTemplate getStringRedisTemplate() {
        if (stringRedisTemplate == null) {
            throw new BusinessException("请自行设置stringRedisTemplate或扫描使用RedisUtilConfig配置后，才可正常使用");
        }
        return stringRedisTemplate;
    }

    /**
     * 指定缓存失效时间
     * @param key  键
     * @param time 时间(秒)
     * @return 指定是否成功
     */
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                return getRedisTemplate().expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("expire key:{}, time:{}", key, time, e);
        }
        return false;
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒)<br />
     * 当 key 不存在时，返回 -2 <br/>
     * 当 key 存在但没有设置剩余生存时间时，返回 -1
     */
    public static long getExpire(String key) {
        try {
            return getRedisTemplate().getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("getExpire key:{}", key, e);
        }
        return 0L;
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public static boolean hasKey(String key) {
        try {
            return getRedisTemplate().hasKey(key);
        } catch (Exception e) {
            log.error("hasKey key:{}", key, e);
        }
        return false;
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     * @return 删除成功数量
     */
    public static long del(String... keys) {
        try {
            if (keys != null && keys.length > 0) {
                if (keys.length == 1) {
                    return getRedisTemplate().delete(keys[0]) ? 1L : 0L;
                } else {
                    return getRedisTemplate().delete(Arrays.asList(keys));
                }
            }
        } catch (Exception e) {
            log.error("del keys:{}", keys, e);
        }
        return 0L;
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        try {
            return StringUtils.isBlank(key) ? null : getRedisTemplate().opsForValue().get(key);
        } catch (Exception e) {
            log.error("get key:{}", key, e);
        }
        return null;
    }

    /**
     * 产生不重复的递增值
     */
    public static Long increase(String key) {
        try {
            return StringUtils.isBlank(key) ? null : getRedisTemplate().opsForValue().increment(key);
        } catch (Exception e) {
            log.error("get key:{}", key, e);
        }
        return null;
    }

    /**
     * 保存k-v
     */
    public static void setStringKey(String key, String value, int expire) {
        try {
            getStringRedisTemplate().opsForValue().set(key, value, expire, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("set key:{}", key, e);
        }
    }


    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public static Object getKey(String key) {
        try {
            return StringUtils.isBlank(key) ? null : getStringRedisTemplate().opsForValue().get(key);
        } catch (Exception e) {
            log.error("get key:{}", key, e);
        }
        return null;
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public static Object getHashKey(String key, String field) {
        try {
            return StringUtils.isBlank(key) ? null : getStringRedisTemplate().opsForHash().get(key, field);
        } catch (Exception e) {
            log.error("get key:{}", key, e);
        }
        return null;
    }

    /**
     * 普通缓存放入
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public static boolean set(String key, Object value) {
        try {
            getRedisTemplate().opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("set key:{}, value:{}", key, value, e);
        }
        return false;
    }

    /**
     * 普通缓存放入并设置时间
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean set(String key, Object value, long time) {
        try {
            boolean r = set(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return r;
        } catch (Exception e) {
            log.error("set key:{} value:{} time:{}", key, value, time, e);
        }
        return false;
    }

    /**
     * 当key不存在时，普通缓存放入
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public static boolean setnx(String key, Object value) {
        try {
            return getRedisTemplate().opsForValue().setIfAbsent(key, value);
        } catch (Exception e) {
            log.error("setnx key:{}, value:{}", key, value, e);
        }
        return false;
    }

    /**
     * 普通缓存放入并设置时间
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean setnx(String key, Object value, long time) {
        try {
            boolean r = setnx(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return r;
        } catch (Exception e) {
            log.error("setnx key:{} value:{} time:{}", key, value, time, e);
        }
        return false;
    }

    /**
     * 递增
     * @param key   键
     * @param delta 要增加几(大于0)
     */
    public static long incr(String key, long delta) {
        if (delta < 0) {
            throw new BusinessException("递增因子必须大于0");
        }
        return getRedisTemplate().opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key   键
     * @param delta 要减少几(小于0)
     */
    public static long decr(String key, long delta) {
        if (delta < 0) {
            throw new BusinessException("递减因子必须大于0");
        }
        return getRedisTemplate().opsForValue().increment(key, -delta);
    }

    /**
     * HashGet
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object hGet(String key, String item) {
        try {
            return getRedisTemplate().opsForHash().get(key, item);
        } catch (Exception e) {
            log.error("hGet key:{}, item:{}", key, item, e);
        }
        return null;
    }


    /**
     * HashGet
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object hGetuser(String key, String userId) {
        try {
            return getRedisTemplate().opsForHash().get(key, userId);
        } catch (Exception e) {
            log.error("hGet key:{}, item:{}", key, e);
        }
        return null;
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public static Map<Object, Object> hGet(String key) {
        try {
            return getRedisTemplate().opsForHash().entries(key);
        } catch (Exception e) {
            log.error("hGet key:{}", key, e);
        }
        return null;
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public static boolean hSet(String key, Map<String, Object> map) {
        try {
            getRedisTemplate().opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("hSet key:{}, map:{}", key, map, e);
        }
        return false;
    }

    /**
     * HashSet 并设置时间
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public static boolean hSet(String key, Map<String, Object> map, long time) {
        try {
            boolean r = hSet(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return r;
        } catch (Exception e) {
            log.error("hSet key:{}, map:{},time:{}", key, map, time, e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value) {
        try {
            getRedisTemplate().opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("hSet key:{}, item:{}, value:{}", key, item, value, e);
        }
        return false;
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hSetUser(String key, String userId, Object value) {
        try {
            getRedisTemplate().opsForHash().put(key, userId, value);
            return true;
        } catch (Exception e) {
            log.error("hSet key:{}, item:{}, value:{}", key, userId, value, e);
        }
        return false;
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value, long time) {
        try {
            boolean r = hSet(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return r;
        } catch (Exception e) {
            log.error("hSet key:{}, item:{}, value:{}, time:{}", key, item, value, time, e);
        }
        return false;
    }

    /**
     * 删除hash表中的值
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static long hDel(String key, Object... items) {
        try {
            return getRedisTemplate().opsForHash().delete(key, items);
        } catch (Exception e) {
            log.error("hDel key:{}, items:{}", key, items, e);
        }
        return 0L;
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static boolean hHasKey(String key, String item) {
        try {
            return getRedisTemplate().opsForHash().hasKey(key, item);
        } catch (Exception e) {
            log.error("hHasKey key:{}, item:{}", key, item, e);
        }
        return false;
    }

    /**
     * 判断hash表中项的数据量
     * @param key 键 不能为null
     * @return 数量
     */
    public static long hSize(String key) {
        try {
            return getRedisTemplate().opsForHash().size(key);
        } catch (Exception e) {
            log.error("hSize key:{}", key, e);
        }
        return 0L;
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     */
    public static double hIncr(String key, String item, double by) {
        return getRedisTemplate().opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     */
    public static double hDecr(String key, String item, double by) {
        return getRedisTemplate().opsForHash().increment(key, item, -by);
    }

    /**
     * 根据key获取Set中的所有值
     * @param key 键
     */
    public static Set<Object> sGet(String key) {
        try {
            return getRedisTemplate().opsForSet().members(key);
        } catch (Exception e) {
            log.error("sGet key:{}", key, e);
        }
        return null;
    }

    /**
     * 根据value从一个set中查询,是否存在
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public static boolean sHasKey(String key, Object value) {
        try {
            return getRedisTemplate().opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("sHasKey key:{},value:{}", key, value, e);
        }
        return false;
    }

    /**
     * 将数据放入set缓存
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static long sSet(String key, Object... values) {
        try {
            return getRedisTemplate().opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("sSet key:{},values:{}", key, values, e);
        }
        return 0L;
    }

    /**
     * 将set数据放入缓存
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static long sSet(String key, long time, Object... values) {
        try {
            Long count = getRedisTemplate().opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            log.error("sSet key:{},time:{},values:{}", key, time, values, e);
        }
        return 0;
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     */
    public static long sSize(String key) {
        try {
            return getRedisTemplate().opsForSet().size(key);
        } catch (Exception e) {
            log.error("sSize key:{}", key, e);
        }
        return 0;
    }

    /**
     * 移除值为value的
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public static long sRemove(String key, Object... values) {
        try {
            return getRedisTemplate().opsForSet().remove(key, values);
        } catch (Exception e) {
            log.error("sRemove key:{},values:{}", key, values, e);
        }
        return 0L;
    }

    /**
     * 获取list缓存的内容
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     */
    public static List<Object> lRange(String key, long start, long end) {
        try {
            return getRedisTemplate().opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("lRange key:{}, start:{}, end:{}", key, start, end, e);
        }
        return null;
    }

    /**
     * 通过索引 获取list中的值
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public static Object lGet(String key, long index) {
        try {
            return getRedisTemplate().opsForList().index(key, index);
        } catch (Exception e) {
            log.error("lGet key:{}, index:{}", key, index, e);
        }
        return null;
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public static boolean lSet(String key, long index, Object value) {
        try {
            getRedisTemplate().opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("lSet key:{}, index:{}, value:{}", key, index, value, e);
        }
        return false;
    }

    /**
     * 将values放入list队尾
     * @param key   键
     * @param value 值
     */
    public static long lRpush(String key, Object... values) {
        try {
            return getRedisTemplate().opsForList().rightPushAll(key, values);
        } catch (Exception e) {
            log.error("lRpush key:{}, values:{}", key, values, e);
        }
        return 0L;
    }

    /**
     * 将values放入list队尾
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public static long lRpush(String key, long time, Object... values) {
        try {
            long r = getRedisTemplate().opsForList().rightPushAll(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return r;
        } catch (Exception e) {
            log.error("lRpush key:{}, time:{}, values:{}", key, time, values, e);
        }
        return 0L;
    }

    /**
     * 将values放入list队头
     * @param key   键
     * @param value 值
     */
    public static long lLpush(String key, Object... values) {
        try {
            return getRedisTemplate().opsForList().leftPushAll(key, values);
        } catch (Exception e) {
            log.error("lLpush key:{}, values:{}", key, values, e);
        }
        return 0L;
    }

    /**
     * 将values放入list队头
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public static long lLpush(String key, long time, Object... values) {
        try {
            long r = getRedisTemplate().opsForList().leftPushAll(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return r;
        } catch (Exception e) {
            log.error("lLpush key:{}, time:{}, values:{}", key, time, values, e);
        }
        return 0L;
    }

    /**
     * 移除N个值为value
     * @param key   键
     * @param count 移除多少个,如<br/>
     *              2 移除从表尾到表头，最先发现的两个<br/>
     *              -1 移除从表尾到表头，第一个<br/>
     *              0 移除表中所有<br/>
     * @param value 值
     * @return 移除的个数
     */
    public static long lRemove(String key, long count, Object value) {
        try {
            return getRedisTemplate().opsForList().remove(key, count, value);
        } catch (Exception e) {
            log.error("lRemove key:{}, count:{}, value:{}", key, count, value, e);
        }
        return 0L;
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     */
    public static long lSize(String key) {
        try {
            return getRedisTemplate().opsForList().size(key);
        } catch (Exception e) {
            log.error("lSize key:{}", key, e);
        }
        return 0L;
    }

    /**
     * 可根据前缀模糊匹配删除key.
     * @param prex the prex key匹配前缀，如需模糊匹配 末尾需带*
     */
    public static void deleteByPrex(String prex) {
        try {
            Set<String> keys = getRedisTemplate().keys(prex);
            if (ObjectUtils.isNotEmpty(keys)) {
                getRedisTemplate().delete(keys);
            }
        } catch (Exception e) {
            log.error("deleteByPrex prex:{}", prex, e);
        }
    }

    /**
     *
     */
    public static List<? extends Object> hgetAll(String key) {
        try {
            return getRedisTemplate().opsForHash().values(key);
        } catch (Exception e) {
            log.error("hgetAll key:{}", key, e);
        }
        return new ArrayList<Object>();
    }

    /*
     * redis分布式锁实现，同时利用expire设置过期时间
     * 返回true就是设置成功
     * */
    public Boolean setNX(String key, String value, long timeout, TimeUnit unit) {
        Boolean isExit = redisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(), value.getBytes());
        //如果设置成功，要设置其过期时间
        if (isExit) {
            redisTemplate.expire(key, timeout, unit);
        }
        return isExit;
    }

}
