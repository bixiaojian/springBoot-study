package com.pancm.server;

import com.pancm.protobuf.KLine;
import com.pancm.protobuf.Snapshot;
import com.pancm.util.DateUtil;
import com.pancm.util.NettyConstants;

import io.netty.channel.ChannelHandlerContext;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

/**
 * @author bixiaojian
 * @Description: ${todo}
 * @date 2019/7/1811:46
 */
@Component
@Slf4j
public class MessageSend {
  private ExecutorService executor = Executors.newFixedThreadPool(1);

  @PostConstruct
  public void send() {
    sendData();
  }

  public void sendData() {
    Runnable task = new Runnable() {
      @Override
      public void run() {
        syncProcessNettyString();
      }
    };
    executor.submit(task);
  }

  public void syncProcessNetty() {
    ChannelHandlerContext ctx = NettyCache.channelMap.get("1");
    while (ctx == null) {
      System.out.println("ctx为空， 再次获取");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      ctx = NettyCache.channelMap.get("1");
    }

    long start = System.currentTimeMillis();
    log.info("开始发送 " + start);

    for (int i = 0; i < 100; i++) {

      Snapshot.SnapshotPro snapshotPro = Snapshot.SnapshotPro
          .newBuilder().setId(System.currentTimeMillis() + "")
          .setName("CU000-" + Thread.currentThread().getName())
          .setCode("CU000")
          .setDate(20190707)
          .setTime(93030)
          .setPrevSettlement(20190707)
          .setOpen(5000000)
          .setHigh(6000000)
          .setLow(4000000)
          .setClose(5000000)
          .setBusinessAmount(500000)
          .setAmount(2000000)
          .setSettlement(6000000)
          .setBidEntrustPx(6000000)
          .setOfferEntrustPx(600000)
          .setBidTotalEntrustAmount(80000)
          .setOfferTotalEntrustAmount(400000)
          .setBusinessBalance(20000)
          .setPreAmount(30000)
          .setChangePrice(20000)
          .setCreateTime(20190606)
          .setTradingDay(20190606)
          .setTimestamp(System.currentTimeMillis())
          .setReceiveTime(20190808)
          .setCurrentAmount(9000000)
          .setPxChange(9000000)
          .setPxChangeRate(9000000)
          .setAmountDelta(9000000)
          .setSec60Chgpct(9000000)
          .setCurrentPxChange(9000000)
          .setCurrentAmountChange(9000000)
          .setPreclose(9000000)
          .setCapitalDeposit(9000000)
          .setCapitalFlow(9000000)
          .setTrends(9000000)
          .setSpeculation(9000000)
          .setTickSize(9000000)
          .setContractUnit(9000000)
          .setExpireDate(9000000)
          .setAvg(9000000)
          .setUp(9000000)
          .setDown(100000 )
          .putExtra("CREATE", System.currentTimeMillis() + "").build();
      ctx.writeAndFlush(snapshotPro);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("发送耗时 ： " + (System.currentTimeMillis() - start));
  }


  public void syncProcessNettyString() {
    ChannelHandlerContext ctx = NettyCache.channelMap.get("1");
    while (ctx == null) {
      System.out.println("ctx为空， 再次获取");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      ctx = NettyCache.channelMap.get("1");
    }
    long start = System.currentTimeMillis();
    log.info("开始发送 " + start);

    for (int i = 0; i < 10000; i++) {

      StringBuilder sb = new StringBuilder();
      sb.append(System.currentTimeMillis())
          .append(",").append("CU000-" + Thread.currentThread().getName())
          .append(",").append("CU000")
          .append(",").append(20190707)
          .append(",").append(93030)
          .append(",").append(20190707)
          .append(",").append(5000000)
          .append(",").append(6000000)
          .append(",").append(4000000)
          .append(",").append(5000000)
          .append(",").append(500000)
          .append(",").append(2000000)
          .append(",").append(6000000)
          .append(",").append(6000000)
          .append(",").append(600000)
          .append(",").append(80000)
          .append(",").append(400000)
          .append(",").append(20000)
          .append(",").append(30000)
          .append(",").append(20000)
          .append(",").append(20190606)
          .append(",").append(20190606)
          .append(",").append(System.currentTimeMillis())
          .append(",").append(20190808)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(9000000)
          .append(",").append(100000 )
          .append(",").append("CREATE"+ System.currentTimeMillis() + "").append(NettyConstants.MSG_SEPARATOR);
      ctx.writeAndFlush(sb.toString());
//      try {
//        Thread.sleep(50);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
    }
    System.out.println("发送耗时 ： " + (System.currentTimeMillis() - start));
  }
}
