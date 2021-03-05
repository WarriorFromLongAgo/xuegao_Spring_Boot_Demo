package com.xuegao.netty_chat_room_server.tomcat1;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.tomcat1
 * <br/> @ClassName：Servlet
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/05 15:42
 */
public abstract class Servlet {
    public void service(Request request, Response response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doPost(Request request, Response response) throws Exception;

    public abstract void doGet(Request request, Response response) throws Exception;
}