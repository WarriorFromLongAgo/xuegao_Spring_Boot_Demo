package com.xuegao.netty_chat_room_server.jhonrain_rg;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import static com.xuegao.netty_chat_room_server.jhonrain_rg.FileProcessHelper.organizedURL;
import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpUtil.setContentLength;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.jhonrain_rg
 * <br/> @ClassName：HttpFileServerHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/3/7 20:38
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private String url;

    public HttpFileServerHandler(String url) {
        this.url = url;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        /** 解码失败 **/
        if (!request.decoderResult().isSuccess()) {
            FileProcessHelper.sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        /** 只支持GET方法 **/
        if (!request.method().equals(HttpMethod.GET)) {
            FileProcessHelper.sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
            return;
        }
        /** 格式化url并判断 **/
        final String uri = request.uri();
        final String path = organizedURL(uri, url);
        if (null == path) {
            FileProcessHelper.sendError(ctx, HttpResponseStatus.FORBIDDEN);
            return;
        }
        /** 对文件路径的判断 **/
        File file = new File(path);
        if (file.isHidden() || !file.exists()) {
            FileProcessHelper.sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }
        /** 判断请求的是否是文件夹 **/
        if (file.isDirectory()) {
            if (uri.endsWith("/")) {
                FileProcessHelper.fileList(ctx, file);
            } else {
                FileProcessHelper.sendRedirect(ctx, uri + "/");
            }
            return;
        }
        /** 文件的判断 **/
        if (!file.isFile()) {
            FileProcessHelper.sendError(ctx, HttpResponseStatus.FORBIDDEN);
            return;
        }

        RandomAccessFile randomAccessFile = null;
        try {
            /** 只读的方式打开文件 **/
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            FileProcessHelper.sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }

        long fileLegth = randomAccessFile.length();
        /** 创建一个Http响应文件大小 **/
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        /** 设置响应文件大小 **/
        setContentLength(response, fileLegth);
        /** 设置content type **/
        FileProcessHelper.setContentTypeHeader(response, file);
        if (isKeepAlive(request)) {
            response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        ctx.write(response);

        ChannelFuture sendFileFuture = null;
        sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLegth, 8192), ctx.newProgressivePromise());
        sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {
                if (total < 0) {
                    System.err.println("Transfer progress: " + progress);
                } else {
                    System.err.println("Transfer progress:" + progress + "/" + total);
                }
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {
                System.out.print("Transfer Successed ! ");
            }
        });
        ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        /** 如果不支持keep-alive ，服务器端主动关闭请求 **/
        if (!isKeepAlive(request)) {
            lastContentFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (ctx.channel().isActive()) {
            FileProcessHelper.sendError(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }
}