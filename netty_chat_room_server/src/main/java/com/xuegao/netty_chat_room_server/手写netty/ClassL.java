package com.xuegao.netty_chat_room_server.手写netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.手写netty
 * <br/> @ClassName：ClassL
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/01 14:14
 */
public class ClassL {
    public static void main(String[] args) {


//         @Override
//         public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
// //        super.channelRead(ctx, msg);
//             logger.info(msg.toString());
//             FullHttpRequest request = null;
//             if (msg instanceof FullHttpRequest){
//                 request = (FullHttpRequest) msg;
//             }
//             int client = request.headers().getInt("client",0);
//             logger.info("client:"+client);
//             if (client == 1){
//                 logger.info("client call");
//                 userClientService.proxy(ctx,request);
//             }else if (client == 2){
//                 logger.info("server call");
//                 routerServerService.keepAlive(ctx,request);
//             }else {
//                 logger.info("other call");
//                 otherService.other(ctx,request);
//             }
//
//         }


    }
}