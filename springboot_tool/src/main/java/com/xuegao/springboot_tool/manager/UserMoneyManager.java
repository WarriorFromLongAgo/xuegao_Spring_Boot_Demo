package com.xuegao.springboot_tool.manager;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.xuegao.springboot_tool.dao.IUserMoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.manager
 * <br/> @ClassName：UserMoneyManager
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/3/29 0:05
 */
@Component
public class UserMoneyManager {

    private IUserMoneyMapper userMoneyMapper;

    @Autowired
    public UserMoneyManager(IUserMoneyMapper userMoneyMapper) {
        this.userMoneyMapper = userMoneyMapper;
    }

    public Boolean decrementMoneyByUserId(String userId, Long money) {
        int count = userMoneyMapper.decrementMoneyByUserId(userId, money);
        if (count < 1) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


}