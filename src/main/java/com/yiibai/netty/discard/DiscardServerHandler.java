package com.yiibai.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理服务器端通道
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
    	ByteBuf in = (ByteBuf) msg;
    	try {
        	// 处理业务
    		System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
		} finally {
			// 以静默方式丢弃接收的数据
			((ByteBuf) msg).release(); // (3)
		}
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 出现异常时关闭连接。
        cause.printStackTrace();
        ctx.close();
    }
}