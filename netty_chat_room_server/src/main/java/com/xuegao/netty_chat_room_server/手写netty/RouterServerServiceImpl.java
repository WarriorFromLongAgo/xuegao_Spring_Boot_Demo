// package com.xuegao.netty_chat_room_server.手写netty;
//
//
// import com.liukai.netty.data.JedisClient;
// import com.liukai.netty.serializer.impl.JSONSerializer;
// import com.liukai.netty.service.RouterServerService;
// import io.netty.channel.ChannelFutureListener;
// import io.netty.handler.codec.http.*;
// import io.netty.handler.codec.http.FullHttpResponse;
//
// import static com.sun.deploy.net.HttpRequest.CONTENT_TYPE;
// import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
// import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
// import static io.netty.handler.codec.http.HttpHeaders.Names.*;
// import static io.netty.handler.codec.http.HttpResponseStatus.OK;
// import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
//
// public class RouterServerServiceImpl implements RouterServerService {
//
//     private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RouterServerServiceImpl.class);
//
//     private static RouterServerService routerServerService = null;
//
//     private JedisClient jedisClient = new JedisClient();
//
//     public void keepAlive(io.netty.channel.ChannelHandlerContext ctx, FullHttpRequest msg) {
//         String key = msg.headers().get("key");
//         String address = msg.headers().get("Host");
//         String s[] = address.split(":");
//         logger.info("key="+key+";"+"address="+address+";"+"host:"+s[0]+";"+"port:"+s[1]+";");
//         jedisClient.hset(key, s[0], s[1]);
//
//         java.util.Map<String,String> map = jedisClient.hGetAll(key);
//
//         JSONSerializer jsonSerializer = new JSONSerializer();
//         byte[] content = jsonSerializer.serialize(map);
//
//         FullHttpResponse response =
//                 new DefaultFullHttpResponse(HTTP_1_1, OK, io.netty.buffer.Unpooled.wrappedBuffer(content));
//         //允许跨域访问
//         response.headers().set(CONTENT_TYPE, "application/json;charset=UTF-8; charset=UTF-8");
//         response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");;
//         response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS,"*");
//         response.headers().set(ACCESS_CONTROL_ALLOW_CREDENTIALS,"true");
//         response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
//
//         boolean keepAlive = HttpUtil.isKeepAlive(msg);
//         if (!keepAlive) {
//             ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//         } else {
//             response.headers().set(CONNECTION, KEEP_ALIVE);
//             ctx.write(response);
//         }
//     }
//
//     public static RouterServerService getInstance(){
//         if (routerServerService == null){
//             routerServerService = new RouterServerServiceImpl();
//         }
//         return routerServerService;
//     }
// }
