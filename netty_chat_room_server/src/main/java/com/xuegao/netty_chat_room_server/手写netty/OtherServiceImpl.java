// package com.xuegao.netty_chat_room_server.手写netty;
//
// import com.liukai.netty.serializer.impl.JSONSerializer;
// import com.liukai.netty.service.OtherService;
// import io.netty.channel.ChannelFutureListener;
// import io.netty.handler.codec.http.*;
//
// import static com.sun.deploy.net.HttpRequest.CONTENT_TYPE;
// import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
// import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
// import static io.netty.handler.codec.http.HttpHeaders.Names.*;
// import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
// import static io.netty.handler.codec.http.HttpResponseStatus.OK;
// import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
//
// public class OtherServiceImpl implements OtherService {
//
//     private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OtherServiceImpl.class);
//
//     private static OtherService otherService = null;
//
//     private OtherServiceImpl(){
//
//     }
//
//     public void other(io.netty.channel.ChannelHandlerContext ctx, FullHttpRequest msg) {
//
//         JSONSerializer jsonSerializer = new JSONSerializer();
//
//         FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, io.netty.buffer.Unpooled.wrappedBuffer(jsonSerializer.serialize("拒绝访问")));
// //        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
//         response.headers().set(CONTENT_TYPE, "application/json;charset=UTF-8");
//         String host = msg.headers().get("Host");
//         logger.info("host:"+host);
//         //允许跨域访问
//         response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");
//         response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS,"*");
//         response.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"GET, POST, PUT,DELETE");
//         response.headers().set(ACCESS_CONTROL_ALLOW_CREDENTIALS,"true");
//
//         response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
//
//         boolean keepAlive = HttpUtil.isKeepAlive(msg);
//         if (!keepAlive) {
//             logger.info("!keepAlive");
//             ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//         } else {
//             logger.info("keepAlive");
//             response.headers().set(CONNECTION, KEEP_ALIVE);
//             ctx.write(response);
//         }
//         ctx.flush();
//     }
//
//     public static OtherService getInstance(){
//         if (otherService == null){
//             otherService = new OtherServiceImpl();
//         }
//         return otherService;
//     }
// }
