package com.xuegao.netty_chat_room_server.serverhandler;

import com.xuegao.netty_chat_room_server.myservice.MyHttpService;
import com.xuegao.netty_chat_room_server.myservice.MyWebSocketService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.serverhandler
 * <br/> @ClassName：WebSocketServerHandler
 * <br/> @Description：
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/6/17 14:12
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private MyHttpService httpService;

    private MyWebSocketService webSocketService;

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * <br/> @Title:
     * <br/> @MethodName:  WebSocketServerHandler
     * <br/> @param httpService:
     * <br/> @param webSocketService:
     * <br/> @Return
     * <br/> @Description:
     * <br/> @author: 花名：xuegao
     * <br/> @date:  2020/6/17 14:46
     */
    public WebSocketServerHandler(MyHttpService httpService, MyWebSocketService webSocketService) {
        super();
        this.httpService = httpService;
        this.webSocketService = webSocketService;
    }

    /**
     * <br/> @Title:
     * <br/> @MethodName:  channelRead0
     * <br/> @param channelHandlerContext:
     * <br/> @param message:
     * <br/> @Return void
     * <br/> @Description:
     * <br/> @author: 花名：xuegao
     * <br/> @date:  2020/6/17 14:47
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object message) throws Exception {
        if (message instanceof FullHttpRequest) {
            httpService.handleHttpRequest(channelHandlerContext, (FullHttpRequest) message);
        } else if (message instanceof WebSocketFrame) {
            webSocketService.handleFrame(channelHandlerContext, (WebSocketFrame) message);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelGroup.add(channelHandlerContext.channel());
        channelGroup.writeAndFlush(new TextWebSocketFrame(channelHandlerContext.channel() + "上线了"));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelGroup.remove(channelHandlerContext.channel());
        channelGroup.writeAndFlush(new TextWebSocketFrame(channelHandlerContext.channel() + "下线了"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        channelGroup.remove(channelHandlerContext.channel());
        channelHandlerContext.close();
        log.info("异常信息：{}", throwable.getMessage());
    }
}