package com.batsandrey.demo.handler;

import com.batsandrey.demo.entity.request.RequestData;
import com.batsandrey.demo.entity.response.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        RequestData requestData = (RequestData) msg;
        String message = requestData.getStringValue();
        ResponseData responseData = new ResponseData();

        if (message.isEmpty() == false && message !=null) {
            responseData.setSequence("1\r\n");
        } else {
            responseData.setSequence("0\r\n");
        }

        ChannelFuture future = ctx.writeAndFlush(responseData);
        future.addListener(ChannelFutureListener.CLOSE);
    }
}