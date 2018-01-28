package com.jhp.netty.chapter01.handler;
import com.jhp.netty.chapter01.model.UserInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * Created by jack on 2018/1/18.
 *
 */
public class NettyLoginClientHandler extends ChannelInboundHandlerAdapter {

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        writeObject(ctx);
    }

    /**
     * 写入对象
     * @param ctx
     * @throws Exception
     */
    private void writeObject(ChannelHandlerContext ctx) throws Exception {
        //写入对象
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("lyn");
        userInfo.setPassWord("201801");
        System.out.println("向服务器端写入");
        ctx.writeAndFlush(userInfo);
    }


    /**
     * 写入字符串
     * @param ctx
     * @throws Exception
     */
    private void writeStr(ChannelHandlerContext ctx) throws Exception {
        //写入字符串
        System.out.println("客户端向服务器短写入登录数据");
        ctx.write("bruce请求登录");
        ctx.flush();
    }



    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        readStr(ctx,msg);
    }


    /**
     * 读取返回来的字符串
     * @param ctx
     * @param msg
     * @throws Exception
     */
    private void readStr(ChannelHandlerContext ctx, Object msg) throws Exception {
        String result = (String) msg;
        System.out.println("客户端接收到服务端的数据是:" + result);

    }

    /**
     * 读取返回来的对象信息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    private void readObject(ChannelHandlerContext ctx, Object msg) throws Exception {
        UserInfo userInfo = (UserInfo) msg;
        System.out.println("客户端接收到服务器端的信息是：" + userInfo.getUserName());
    }


}
