package org.hum.lab.netty.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.hum.lab.netty.common.NettyServerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 并发写入Socket是否需要加锁？
 * 
 * <pre>
 *    代码实现场景：10个线程写入大数据量，看接收时是否会出现错乱的情况
 * </pre>
 * 
 * <pre>
 *    测试结果：Sever端收到的字符果然是相互穿插的，与发送顺序不一致，出现了收到半包的现象
 * </pre>
 * 
 * <pre>
 * 	  结论：并发写入Socket需要加锁，否则会出现乱序的现象，但加锁后由于对端接收缓慢，
 * 		   也会影响发送方写入速度，极端情况下，接收端窗口为零时，发送方线程直接Block掉。
 *</pre>
 * @author hudaming
 */
public class IsSocketWriteNeedLock extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof ByteBuf) {
			System.out.println("[reveive msg]" + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
		}
		ctx.fireChannelRead(msg);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		NettyServerFactory.start(10086, () -> {
			return new ChannelHandler[] { new IsSocketWriteNeedLock() };
		});
		System.in.read();
	}

}

// 线程安全发送
class IsSocketWriteNeedLock_SynchronizedSend {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 10086);
		int threadCount = 10;
		int writeCharCnt = 1024 * 1024;
		final OutputStream outputStream = socket.getOutputStream();
		ExecutorService ThreadPool = Executors.newFixedThreadPool(threadCount);
		CountDownLatch latch = new CountDownLatch(threadCount);
		Lock lock = new ReentrantLock();
		for (int i = 0; i < threadCount; i++) {
			final int num = i;
			ThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						StringBuilder sbuilder = new StringBuilder();
						for (int i = 0; i < writeCharCnt; i++) {
							sbuilder.append(num);
						}
						System.out.println("Thread-" + num + " has ready.");
						latch.countDown();
						latch.await();
						lock.lock();
						try {
							outputStream.write(sbuilder.toString().getBytes());
							outputStream.flush();
						} finally {
							lock.unlock();
						}
						System.out.println("Thread-" + num + " flush over.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		System.in.read();
		socket.close();
	}
}

// 并行发送
class IsSocketWriteNeedLock_ConcurrencySend {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 10086);
		int threadCount = 10;
		int writeCharCnt = 1024 * 1024;
		final OutputStream outputStream = socket.getOutputStream();
		ExecutorService ThreadPool = Executors.newFixedThreadPool(threadCount);
		CountDownLatch latch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			final int num = i;
			ThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						StringBuilder sbuilder = new StringBuilder();
						for (int i = 0; i < writeCharCnt; i++) {
							sbuilder.append(num);
						}
						System.out.println("Thread-" + num + " has ready.");
						latch.countDown();
						latch.await();
						outputStream.write(sbuilder.toString().getBytes());
						outputStream.flush();
						System.out.println("Thread-" + num + " flush over.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		System.in.read();
		socket.close();
	}
}