package org.hum.lab.netty.demo;

import org.hum.lab.netty.common.NettyServerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyStickyServerTest extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf byteBuf = (ByteBuf) msg;
		int len = byteBuf.readableBytes();
		byte[] bytes = new byte[len];
		byteBuf.readBytes(bytes);
		System.out.println("read length(" + len + "), bytes=" + new String(bytes));
		ctx.fireChannelRead(msg);
	}

	public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException {
		NettyServerFactory.start(10086, () -> {
			return new ChannelHandler[] { new NettyStickyServerTest() };
		});
	}
}
