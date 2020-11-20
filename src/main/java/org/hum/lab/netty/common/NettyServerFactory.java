package org.hum.lab.netty.common;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerFactory {
	
	public static ChannelFuture start(int port, ChannelHandler... handlers) throws InterruptedException {
		ServerBootstrap bootstrap = new ServerBootstrap();
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(4);
		bootstrap.group(bossGroup, workerGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(handlers);
			}
		});
		ChannelFuture future = bootstrap.bind(port);
		future.addListener(f -> {
			if (f.isSuccess()) {
				System.out.println("server started;");
			} else {
				System.out.println("server failed;");
			}
		});
		return future;
	}
}
