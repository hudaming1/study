package org.hum.lab.netty.server;

import java.io.IOException;

import org.hum.lab.netty.common.NettyServerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyStardardServerLab extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(msg);
		ctx.fireChannelRead(msg);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		NettyServerFactory.start(10086, new NettyStardardServerLab());
		System.out.println("1111");
		System.in.read();
	}
}
