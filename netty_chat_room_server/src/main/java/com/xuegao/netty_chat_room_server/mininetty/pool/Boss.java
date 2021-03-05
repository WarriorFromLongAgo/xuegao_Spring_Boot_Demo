package com.xuegao.netty_chat_room_server.mininetty.pool;

import java.nio.channels.ServerSocketChannel;

public interface Boss {

	void registerAcceptChannelTask(ServerSocketChannel serverChannel);

}
