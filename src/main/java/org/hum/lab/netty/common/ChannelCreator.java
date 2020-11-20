package org.hum.lab.netty.common;

import io.netty.channel.ChannelHandler;

public interface ChannelCreator {

	public ChannelHandler[] newChannelHandlers();
}
