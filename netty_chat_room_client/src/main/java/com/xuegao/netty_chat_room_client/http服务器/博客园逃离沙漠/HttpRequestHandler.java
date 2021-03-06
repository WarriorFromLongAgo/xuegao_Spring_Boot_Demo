package com.xuegao.netty_chat_room_client.http服务器.博客园逃离沙漠;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);

    /*
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ ");
        super.channelActive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        //100 Continue
        if (HttpUtil.is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.CONTINUE));
        }
        // 获取请求的uri
        String uri = req.uri();
        Map<String, String> resMap = new HashMap<>();
        resMap.put("method", req.method().name());
        resMap.put("uri", uri);
        System.out.println(req.protocolVersion().majorVersion());
        System.out.println(req.protocolVersion().minorVersion());
        System.out.println(req.protocolVersion().protocolName());
        System.out.println(req.protocolVersion().text());
        System.out.println(resMap);

        HttpMethod method = req.method();
        if (method.equals(HttpMethod.GET)) {
            QueryStringDecoder queryDecoder = new QueryStringDecoder(uri, StandardCharsets.UTF_8);
            String path = queryDecoder.path();
            log.info(path);
            String rawPath = queryDecoder.rawPath();
            log.info(rawPath);
            String rawQuery = queryDecoder.rawQuery();
            log.info(rawQuery);
            String uri2 = queryDecoder.uri();
            log.info(uri2);
            Map<String, List<String>> uriAttributes = queryDecoder.parameters();
            //此处仅打印请求参数（你可以根据业务需求自定义处理）
            for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
                for (String attrVal : attr.getValue()) {
                    log.info(attr.getKey() + "=" + attrVal);
                }
            }
        } else if (method.equals(HttpMethod.POST)) {
            //POST请求,由于你需要从消息体中获取数据,因此有必要把msg转换成FullHttpRequest
            //根据不同的Content_Type处理body数据
            dealWithContentType(req);
        }

        String msg = "<html><head><title>test</title></head><body>你请求uri为：" + uri + "</body></html>";
        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, msg.length());


        // 路由 请求
        // URI uri = new URI("http://127.0.0.1:8000");
        // String msg = "Are you ok?";
        // DefaultFullHttpRequest request = new DefaultFullHttpRequest(
        //         HttpVersion.HTTP_1_1, HttpMethod.POST, uri.toASCIIString(),
        //         Unpooled.wrappedBuffer(msg.getBytes()));
        // // 构建http请求
        // request.headers().set(HttpHeaderNames.HOST, host);
        // request.headers().set(HttpHeaderNames.CONNECTION,
        //         HttpHeaderNames.CONNECTION);
        // request.headers().set(HttpHeaderNames.CONTENT_LENGTH,
        //         request.content().readableBytes());
        // request.headers().set("messageType", "normal");
        // request.headers().set("businessType", "testServerState");
        // // 发送http请求
        // f.channel().write(request);
        // f.channel().flush();


        // JSONSerializer jsonSerializer = new JSONSerializer();
        // //将Java对象序列化成为二级制数据包
        // byte[] content = jsonSerializer.serialize(user);
        // FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content));
        // response.headers().set(CONTENT_TYPE, "text/plain");
        // response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        //
        // boolean keepAlive = HttpUtil.isKeepAlive(request);
        // if (!keepAlive) {
        //     ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        // } else {
        //     response.headers().set(CONNECTION, KEEP_ALIVE);
        //     ctx.write(response);
        // }

        // 将html write到客户端
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 简单处理常用几种 Content-Type 的 POST 内容（可自行扩展）
     * @throws Exception
     * @param req
     */
    private void dealWithContentType(FullHttpRequest req) throws Exception{
        String contentType = getContentType(req);
        //可以使用HttpJsonDecoder
        if("application/json".equals(contentType)){
            String jsonStr = req.content().toString(StandardCharsets.UTF_8);
            // JSONObject obj = JSON.parseObject(jsonStr);
            // for(Map.Entry<String, Object> item : obj.entrySet()){
            //     logger.info(item.getKey()+"="+item.getValue().toString());
            // }

        }else if(contentType.equals("application/x-www-form-urlencoded")){
            //方式一：使用 QueryStringDecoder
            // String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            // QueryStringDecoder queryDecoder = new QueryStringDecoder(jsonStr, false);
            // Map<String, List<String>> uriAttributes = queryDecoder.parameters();
            // for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
            //     for (String attrVal : attr.getValue()) {
            //         logger.info(attr.getKey() + "=" + attrVal);
            //     }
            // }

        }else if(contentType.equals("multipart/form-data")){
            //TODO 用于文件上传
        }else{
            //do nothing...
        }
    }
    private String getContentType(FullHttpRequest req){
        String typeStr = req.headers().get("Content-Type");
        String[] list = typeStr.split(";");
        return list[0];
    }
    //
    // 作者：穆书伟
    // 链接：https://juejin.cn/post/6844903689543647240
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
