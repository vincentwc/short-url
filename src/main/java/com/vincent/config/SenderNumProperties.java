package com.cecloud.cdp.shorturl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wang_cheng
 * @date 2022/08/24 11:04
 * @desc 号码分发属性
 **/
@Data
@Component
@ConfigurationProperties(prefix = "sender-num.segment")
public class SenderNumProperties {

    /**
     * 区段信息
     */
    private int segment;
}

