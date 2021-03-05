package com.xuegao.netty_chat_room_server.tomcat1;

/**
 * @author luyanan
 * @since 2019/9/19
 * <p></p>
 **/
public class SecondServlet extends Servlet {


    @Override
    public void doPost(Request request, Response response) throws Exception{
        response.write("SecondServlet");
    }

    @Override
    public void doGet(Request request, Response response) throws Exception{

        this.doPost(request, response);
    }
}