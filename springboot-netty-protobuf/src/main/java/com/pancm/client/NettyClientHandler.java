package com.pancm.client;

import com.google.gson.Gson;

import com.pancm.protobuf.KLine;
import com.pancm.protobuf.KLine.KLineCache;
import com.pancm.protobuf.Snapshot;
import com.pancm.protobuf.UserInfo;
import com.pancm.protobuf.UserInfo.UserMsg;
import com.pancm.util.DateUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author pancm
 * @Title: NettyClientHandler
 * @Description: 客户端业务逻辑实现
 * @Version:1.0.0
 * @date 2017年10月8日
 */
@Slf4j
@Service("nettyClientHandler")
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

  @Autowired
  private NettyClient nettyClient;

  /**
   * 循环次数
   */
  private int fcount = 1;

  private int rcount = 0;

  /**
   * 建立连接时
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("建立连接时：" + new Date());
    ctx.fireChannelActive();
  }

  /**
   * 关闭连接时
   */
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("关闭连接时：" + new Date());
    final EventLoop eventLoop = ctx.channel().eventLoop();
    nettyClient.doConnect(new Bootstrap(), eventLoop);
    super.channelInactive(ctx);
  }

  /**
   * 心跳请求处理 每4秒发送一次心跳请求;
   */
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
//    log.info("循环请求的时间：" + new Date() + "，次数" + fcount);
    if (obj instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) obj;
      if (IdleState.WRITER_IDLE.equals(event.state())) { // 如果写通道处于空闲状态,就发送心跳命令
        UserMsg.Builder userState = UserMsg.newBuilder().setState(2);
        ctx.channel().writeAndFlush(userState);
        fcount++;
      }
    }
  }

  /**
   * 业务逻辑处理
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    // 如果不是protobuf类型的数据
    if (!(msg instanceof Snapshot.SnapshotPro)) {
      System.out.println("未知数据!" + msg);
      return;
    }
    try {

      // 得到protobuf的数据
      Snapshot.SnapshotPro snapshotPro = (Snapshot.SnapshotPro) msg;
      // 进行相应的业务处理。。。
      // 这里就从简了，只是打印而已
//      System.out.println(
//          "客户端接受到的用户信息: " + new Gson().toJson(kLineCache));
      rcount++;
      log.info("次数：{}，发送耗时：{} ， cur : {}", rcount, (System.currentTimeMillis() - snapshotPro.getTimestamp()),
          System.currentTimeMillis());

      // 这里返回一个已经接受到数据的状态
//      UserMsg.Builder userState = UserMsg.newBuilder().setState(1);
//      ctx.writeAndFlush(userState);
//      System.out.println("成功发送给服务端!");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ReferenceCountUtil.release(msg);
    }
  }

}

