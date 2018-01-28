package com.jhp.netty.chapter01.client;

import com.jhp.netty.chapter01.handler.NettyLoginClientHandler;
import com.jhp.netty.chapter01.initializer.NettyLoginClientInitialzer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by jack on 2018/1/18.
 * 客户端的主要工作是
 1，连接到服务端
 2，向服务端发送数据数据
 3，处理服务端返回的数据
 4，关闭连接
 */
public class NettyLoginClient {
    public static void main(String[] args) {
        System.out.println("客户端成功启动...");
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(clientGroup);

            bootstrap.channel(NioSocketChannel.class);

            bootstrap.option(ChannelOption.SO_KEEPALIVE,true);

            bootstrap.handler(new NettyLoginClientInitialzer());

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",8080).sync();
            channelFuture.channel().closeFuture().sync();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            //如果在服务端需要返回数据给客户端 这个时候这个不能关闭
            clientGroup.shutdownGracefully();
        }
    }

}
