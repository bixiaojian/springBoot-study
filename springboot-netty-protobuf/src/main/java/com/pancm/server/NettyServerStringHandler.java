package com.pancm.server;

import com.pancm.protobuf.UserInfo.UserMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import org.springframework.stereotype.Service;

/**
 * @author pancm
 * @Title: NettyServerHandler
 * @Description: 服务端业务逻辑
 * @Version:1.0.0
 * @date 2017年10月8日
 */
@Service("nettyServerStringHandler")
@ChannelHandler.Sharable
public class NettyServerStringHandler extends ChannelInboundHandlerAdapter {

  /**
   * 空闲次数
   */
  private int idle_count = 1;
  /**
   * 发送次数
   */
  private int count = 1;


  /**
   * 建立连接时，发送消息
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
    NettyCache.channelMap.put("1", ctx);
//    UserInfo.UserMsg userMsg = UserInfo.UserMsg.newBuilder().setId(1).setAge(18).setName("xuwujing").setState(0)
//        .build();
//    ctx.writeAndFlush(userMsg);
//    KLine.KLineCache kLineCache = KLine.KLineCache.newBuilder().setAccuBargainAmount(0).setAccuBargainSum(0)
//        .setAccuHighPrice(1234L).setAccuLowPrice(0).setAmount(123232).setAmountChange(0).setAvg(22134)
//        .setBarTime(Long.valueOf(DateUtil.Date2String(new Date(),DateUtil.YYYYMMDDHHmmss))).setBusinessAmount(21231)
//        .setBusinessAmountChange(0).setBusinessBalance(213424324).setChangeOI(0).setClose(1234).setCloseChange(43234)
//        .setCloseChangeRate(22).setDate(20190718).setHigh(1243).setLastClose(0).setLow(2123).setOpen(2123)
//        .setPeriod(1).setPeriodTime(1123).setRangeOfPrice(0).setRangePCT(0).setSettlement(1233).setStartDate(20190718)
//        .setStartMin(0).setSumToEndBusinessAmount(0).setSumToEndBusinessBalance(0).setTime(1234).setTradingDate(0)
//        .setTradingDate(20190712).setUpdateTime(0).build();
//    ctx.writeAndFlush(kLineCache);
    super.channelActive(ctx);
  }

  /**
   * 超时处理 如果5秒没有接受客户端的心跳，就触发; 如果超过两次，则直接关闭;
   */
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
//    if (obj instanceof IdleStateEvent) {
//      IdleStateEvent event = (IdleStateEvent) obj;
//      if (IdleState.READER_IDLE.equals(event.state())) { // 如果读通道处于空闲状态，说明没有接收到心跳命令
//        System.out.println("已经5秒没有接收到客户端的信息了");
//        if (idle_count > 1) {
//          System.out.println("关闭这个不活跃的channel");
//          ctx.channel().close();
//        }
//        idle_count++;
//      }
//    } else {
      super.userEventTriggered(ctx, obj);
//    }
  }

  /**
   * 业务逻辑处理
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("第" + count + "次" + ",服务端接受的消息:" + msg);

    count++;
  }

  /**
   * 异常处理
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
