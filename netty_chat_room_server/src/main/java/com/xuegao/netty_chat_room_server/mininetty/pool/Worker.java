package com.xuegao.netty_chat_room_server.mininetty.pool;

import java.nio.channels.SocketChannel;

public interface Worker {

	void registerNewChannelTask(SocketChannel channel);

}
