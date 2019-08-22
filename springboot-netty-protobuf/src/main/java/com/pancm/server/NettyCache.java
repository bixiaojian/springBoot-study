package com.pancm.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import lombok.Data;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bixiaojian
 * @Description: ${todo}
 * @date 2019/7/1811:42
 */
@Data
public class NettyCache {
  public static Map<String, ChannelHandlerContext> channelMap = new ConcurrentHashMap<>();
  public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
