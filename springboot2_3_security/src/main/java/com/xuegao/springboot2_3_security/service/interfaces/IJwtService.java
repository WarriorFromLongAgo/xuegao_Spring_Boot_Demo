package com.xuegao.springboot2_3_security.service.interfaces;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.service.interfaces
 * <br/> @ClassName：IJwtService
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/30 0:20
 */
public interface IJwtService {

    String login(String username, String password);
}