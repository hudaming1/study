package org.hum.lab.netty.demo;

import org.hum.lab.netty.common.NettyServerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyStickyServerTest extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	System.out.println(msg);
    	ByteBuf byteBuf = (ByteBuf) msg;
    	byte[] bytes = new byte[byteBuf.readableBytes()];
    	byteBuf.readBytes(bytes);
    	System.out.println(new String(bytes));
    	ctx.fireChannelRead(msg);
    }
    
    public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException {
		NettyServerFactory.start(10086, NettyStickyServerTest.class.newInstance());
	}
}
