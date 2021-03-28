package com.xuegao.springboot_tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.springboot_tool.model.doo.UserMoney;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.dao
 * <br/> @ClassName：IMqMapper
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/3/29 0:03
 */
@Repository
public interface IUserMoneyMapper extends BaseMapper<UserMoney> {

    Integer decrementMoneyByUserId(@Param("userId") String userId,
                                   @Param("money") Long money);
}