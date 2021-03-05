package com.xuegao.netty_chat_room_server.tomcat2;

import io.netty.handler.codec.http.HttpMethod;

public abstract class WuzzServlet {
    public void service(WuzzRequest request, WuzzResponse response) throws Exception {

        //由service方法来决定，是调用doGet或者调用doPost
        if (HttpMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(WuzzRequest request, WuzzResponse response) throws Exception;

    public abstract void doPost(WuzzRequest request, WuzzResponse response) throws Exception;
}


