package com.jhp.netty.chapter01.server;

import com.jhp.netty.chapter01.handler.NettyLoginServerHandler;
import com.jhp.netty.chapter01.initializer.NettyLoginServerInitialzer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by jack on 2018/1/18.
 * 客户端向服务端发送登录账号和用户密码
 * 服务端接受客户端发过来的信息,判断username=jhp,pwd=123
 * 如果正确,返回给客户端登录成功,否则返回对应的失败信息给客户度
 * 基于短链接
 */
public class NettyLoginServer {
    public static void main(String[] args) {
        System.out.println("服务端成功启动....");
        EventLoopGroup mainGroup = new NioEventLoopGroup(3);
        EventLoopGroup subGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(mainGroup,subGroup);

            serverBootstrap.channel(NioServerSocketChannel.class);

            serverBootstrap.option(ChannelOption.SO_BACKLOG,128)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new NettyLoginServerInitialzer());;

            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();

            channelFuture.channel().closeFuture().sync();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }
}
