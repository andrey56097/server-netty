package com.batsandrey.demo.decoder;

import com.batsandrey.demo.entity.request.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class RequestDataDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        RequestData data = new RequestData();

        byte[] bytes = new byte[in.readableBytes()];
        try {
            in.getBytes(in.readerIndex(), bytes);
            String message = new String(bytes, StandardCharsets.UTF_8).trim();
            data.setStringValue(message);
            log.info("[Decoder] data successfully received - {}", message);
        } catch (Exception exception) {
            exception.printStackTrace();
            data.setStringValue(null);
            log.info("[Decoder] data was not received");
        }

        in.readShort();
        out.add(data);
    }
}
