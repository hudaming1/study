package org.hum.lab.netty.server;

import java.io.IOException;

import org.hum.lab.netty.common.NettyServerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyStardardServerLab extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof ByteBuf) {
			System.out.println("[reveive msg]" + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
		}
		ctx.fireChannelRead(msg);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		NettyServerFactory.start(10086, () -> {
			return new ChannelHandler[] { new NettyStardardServerLab() };
		});
		System.in.read();
	}
}
