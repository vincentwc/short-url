package com.vincent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.entity.ShortUrlMap;
import com.vincent.utils.ResultVO;

import java.util.Optional;

/**
 * @author vincent
 */
public interface IShortUrlMapService extends IService<ShortUrlMap> {

    /**
     * 查询并生成短网址
     *
     * @param url
     * @return
     */
    ResultVO<String> createShortUrl(String url);

    /**
     * 根据短链接获取长链接网址信息
     *
     * @param shortUrl
     * @return
     */
    Optional<String> getUrlByShortUrl(String shortUrl);

    /**
     * 根据长链接的hash值获取对应的映射信息
     *
     * @param urlHash
     * @return
     */
    Optional<ShortUrlMap> getShortUrlMapByUrlHash(String urlHash);

    /**
     * 根据长链接获取对应的映射信息
     *
     * @param urlHash
     * @return
     */
    Optional<ShortUrlMap> getShortUrlMapByUrl(String url);
}
