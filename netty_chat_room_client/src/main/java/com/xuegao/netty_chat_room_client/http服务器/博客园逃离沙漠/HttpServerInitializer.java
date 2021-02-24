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
        pipeline.addLast(new HttpServerCodec());// http 编解码
        // 把单个http请求转为FullHttpReuest或FullHttpResponse
        // http 消息聚合器
        pipeline.addLast("httpAggregator", new HttpObjectAggregator(512 * 1024));
        pipeline.addLast(new HttpRequestHandler());// 请求处理器
    }
}
