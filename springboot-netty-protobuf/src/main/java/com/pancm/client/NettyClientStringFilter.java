package com.pancm.client;

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
 * @Title: NettyClientFilter
 * @Description: Netty客户端 过滤器
 * @Version:1.0.0
 * @date 2017年10月8日
 */
@Component
public class NettyClientStringFilter extends ChannelInitializer<SocketChannel> {

  @Autowired
  private NettyClientStringHandler nettyClientHandler;


  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline ph = ch.pipeline();
    /*
     * 解码和编码，应和服务端一致
     * */
    ph.addLast(new StringEncoder());
    ByteBuf buf = Unpooled.copiedBuffer(NettyConstants.MSG_SEPARATOR.getBytes());
    ph.addLast(new DelimiterBasedFrameDecoder(1024,buf));
    ph.addLast(new StringDecoder());
    ph.addLast(new IdleStateHandler(60 * 10, 60 * 5, 60 * 10, TimeUnit.SECONDS));

    //业务逻辑实现类
    ph.addLast("nettyClientHandler", nettyClientHandler);

  }
}
