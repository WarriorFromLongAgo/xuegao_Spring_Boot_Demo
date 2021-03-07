// package com.xuegao.netty_chat_room_server.手写netty;
//
// import com.liukai.netty.data.JedisClient;
// import com.liukai.netty.proxyclient.HttpProxyInitializer;
// import com.liukai.netty.service.UserClientService;
// import io.netty.channel.ChannelFuture;
// import io.netty.channel.ChannelFutureListener;
//
// public class UserClientServiceImpl implements UserClientService {
//
//     private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserClientServiceImpl.class);
//
//     private static UserClientServiceImpl userClientService = null;
//     private JedisClient jedisClient = new JedisClient();
//
//     private UserClientServiceImpl(){
//
//     }
//     public void proxy(final io.netty.channel.ChannelHandlerContext ctx, final io.netty.handler.codec.http.FullHttpRequest msg) {
//
//         String key = msg.headers().get("key");
//         java.util.Map<String,String> address = jedisClient.hGetAll(key);
//         String host = address.get("host");
//         int port = Integer.parseInt(address.get("port"));
//         logger.info(host+":"+port);
//
//         //连接至目标服务器
//         io.netty.bootstrap.Bootstrap bootstrap = new io.netty.bootstrap.Bootstrap();
//         bootstrap.group(ctx.channel().eventLoop()) // 注册线程池
//                 .channel(ctx.channel().getClass()) // 使用NioSocketChannel来作为连接用的channel类
//                 .handler(new HttpProxyInitializer(ctx.channel()));
//
//         ChannelFuture cf = bootstrap.connect(host, port);
//         cf.addListener(new ChannelFutureListener() {
//             public void operationComplete(ChannelFuture future) throws Exception {
//                 if (future.isSuccess()) {
//
//                     future.channel().writeAndFlush(msg);
//                 } else {
//                     ctx.channel().close();
//                 }
//             }
//         });
//     }
//
//     public static UserClientServiceImpl getInstance(){
//         if (userClientService == null){
//             userClientService = new UserClientServiceImpl();
//         }
//         return userClientService;
//     }
//
//
// }
