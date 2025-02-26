package com.pancm.client;

import com.pancm.protobuf.KLine;
import com.pancm.protobuf.Snapshot;
import com.pancm.protobuf.UserInfo.UserMsg;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
public class NettyClientFilter extends ChannelInitializer<SocketChannel> {

  @Autowired
  private NettyClientHandler nettyClientHandler;


  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline ph = ch.pipeline();
    /*
     * 解码和编码，应和服务端一致
     * */
    //入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
    ph.addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));

    //传输的协议 Protobuf
    ph.addLast(new ProtobufVarint32FrameDecoder());
    ph.addLast(new ProtobufDecoder(Snapshot.SnapshotPro.getDefaultInstance()));
    ph.addLast(new ProtobufVarint32LengthFieldPrepender());
    ph.addLast(new ProtobufEncoder());

    //业务逻辑实现类
    ph.addLast("nettyClientHandler", nettyClientHandler);

  }
}
