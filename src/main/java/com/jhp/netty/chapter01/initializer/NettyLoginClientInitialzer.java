package com.jhp.netty.chapter01.initializer;

import com.jhp.netty.chapter01.decode.RpcDecoder;
import com.jhp.netty.chapter01.encode.RpcEncoder;
import com.jhp.netty.chapter01.handler.NettyLoginClientHandler;
import com.jhp.netty.chapter01.model.UserInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by jack on 2018/1/18.
 */
public class NettyLoginClientInitialzer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {

        //添加自定义的编码/解码

        ch.pipeline().addLast(new RpcDecoder(UserInfo.class));
        ch.pipeline().addLast(new RpcEncoder(UserInfo.class));
        //ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new StringDecoder());

        //ch.pipeline().addLast(new ObjectEncoder());
        //ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

        ch.pipeline().addLast(new NettyLoginClientHandler());


    }
}
