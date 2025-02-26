package com.pancm.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author pancm
 * @Title: NettyClientApp
 * @Description: Netty 客户端主程序
 * @Version:1.0.0
 * @date 2018年7月11日
 */
@SpringBootApplication
public class NettyClientApp {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
    ApplicationContext context = SpringApplication.run(NettyClientApp.class, args);
    NettyClient nettyClient = context.getBean(NettyClient.class);
    nettyClient.run();

//    for (int i = 0; i < 10 ; i++) {
//      new Thread(() -> {
//        NettyClient nettyClient = context.getBean(NettyClient.class);
//        nettyClient.run();
//      }).start();
//    }

  }

}
