package com.jhp.netty.chapter01.handler;

import com.jhp.netty.chapter01.model.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetAddress;
import java.nio.charset.Charset;

/**
 * Created by jack on 2018/1/18.
 */
public class NettyLoginServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        //ctx.writeAndFlush("客户端"+ InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ \n");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        readObject(ctx,msg);
    }


    private void readStr(ChannelHandlerContext ctx, Object msg) throws Exception {
        //这里不能用instance of 来判断的实例 需要仔细思考
        String message = (String)msg;
        System.out.println("服务器端接受到客户的登录信息是:"+message);
        System.out.println("服务端向客户端写入登录结果信息");
        ctx.writeAndFlush("恭喜您,jiahp登录成功");
        ctx.close();
    }


    private void readObject(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取信息显示
        UserInfo userInfo = (UserInfo) msg;
        System.out.println("服务器端接收到的客户端的信息是：" + userInfo.getUserName());

        System.out.println("服务端向客户端写入登录结果信息:恭喜您,lyn登录成功");
        ctx.writeAndFlush("恭喜您,lyn登录成功");
        ctx.close();

    }



}

