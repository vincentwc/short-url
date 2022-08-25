package com.cecloud.cdp.shorturl.utils;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * @author wang_cheng
 * @date 2022/08/24 17:29
 * @desc
 **/
public class StringUtils {

    private StringUtils() {
    }

    /**
     * @param str  字符串
     * @param seed
     * @return hash之后的字符串
     */
    public static String getMurMurHash_128(String str, int seed) {
        HashFunction function = Hashing.murmur3_128(seed);
        return function.hashString(str, StandardCharsets.UTF_8).toString();
    }

    /**
     * @param str 字符串
     * @return hash之后的字符串
     */
    public static String getMurMurHash_128(String str) {
        HashFunction function = Hashing.murmur3_128();
        return function.hashString(str, StandardCharsets.UTF_8).toString();
    }
}
