/**
 * *#                                                                      #
 * #                      Copyright (c) 2017 by                           #
 * #          Shanghai Stock Exchange (SSE), Shanghai, China              #
 * #                       All rights reserved.                           #
 * #                                                                      #
 * *
 */
package com.haah.bear.api.enums;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public enum  LongSerializer implements RedisSerializer<Long> {
    INSTANCE;

    @Override
    public byte[] serialize(Long aLong) throws SerializationException {
        if (null != aLong){
            return aLong.toString().getBytes();
        }else {
            return new byte[0];
        }
    }

    @Override
    public Long deserialize(byte[] bytes) throws SerializationException {
        if (bytes.length > 0){
            return Long.parseLong(new String(bytes));
        }else {
            return null;
        }
    }
}
