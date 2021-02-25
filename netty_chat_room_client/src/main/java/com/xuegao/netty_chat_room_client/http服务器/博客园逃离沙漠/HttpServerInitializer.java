package com.xuegao.netty_chat_room_client.http服务器.博客园逃离沙漠;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // http 编解码
        // 或者使用HttpRequestDecoder & HttpResponseEncoder, HttpRequestDecoder 和 HttpResponseEncoder 的结合。
        pipeline.addLast(new HttpServerCodec());
        // 把单个http请求转为FullHttpReuest或FullHttpResponse
        // http 消息聚合器 在处理POST消息体时需要加上
        pipeline.addLast("httpAggregator", new HttpObjectAggregator(512 * 1024));
        pipeline.addLast(new HttpRequestHandler());// 请求处理器
    }
}
