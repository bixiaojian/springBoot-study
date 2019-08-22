package com.pancm.server;

import com.pancm.protobuf.Snapshot;
import com.pancm.util.NettyConstants;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author pancm
 * @Title: HelloServerInitializer
 * @Description: Netty 服务端过滤器
 * @Version:1.0.0
 * @date 2017年10月8日
 */
@Component
public class NettyServerStringFilter extends ChannelInitializer<SocketChannel> {

  @Autowired
  private NettyServerStringHandler nettyServerHandler;

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline ph = ch.pipeline();

    //入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
    ph.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
    // 解码和编码，应和客户端一致
    //传输的协议 Protobuf
    ch.pipeline().addLast(new StringEncoder());
    ByteBuf buf = Unpooled.copiedBuffer(NettyConstants.MSG_SEPARATOR.getBytes());
    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
    ch.pipeline().addLast(new StringDecoder());

    //业务逻辑实现类
    ph.addLast("nettyServerHandler", nettyServerHandler);
  }
}
