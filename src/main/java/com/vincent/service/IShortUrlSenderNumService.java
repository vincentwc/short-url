package com.vincent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.entity.ShortUrlSenderNum;

import java.util.Optional;

/**
 * @author vincent
 */
public interface IShortUrlSenderNumService extends IService<ShortUrlSenderNum> {


    /**
     * 创建号码分发区段
     * @return
     */
    Optional<ShortUrlSenderNum> createSenderNumSegment();
}
