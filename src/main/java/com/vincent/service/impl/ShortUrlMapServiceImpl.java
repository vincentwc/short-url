package com.vincent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cecloud.cdp.shorturl.utils.ParamConstant;
import com.cecloud.cdp.shorturl.utils.ShortURLUtils;
import com.cecloud.cdp.shorturl.utils.StringUtils;
import com.vincent.entity.ShortUrlMap;
import com.vincent.entity.ShortUrlSenderNum;
import com.vincent.mapper.ShortUrlMapMapper;
import com.vincent.service.IShortUrlMapService;
import com.vincent.service.IShortUrlSenderNumService;
import com.vincent.utils.ResultCodeEnum;
import com.vincent.utils.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author vincent
 */
@Slf4j
@Service
public class ShortUrlMapServiceImpl extends ServiceImpl<ShortUrlMapMapper, ShortUrlMap> implements IShortUrlMapService {

    private static final AtomicLong TMP_START_NUM = new AtomicLong();
    private static final AtomicLong TMP_END_NUM = new AtomicLong();

    @Resource
    private IShortUrlSenderNumService senderNumService;

    @Override
    public ResultVO<String> createShortUrl(String url) {
        // 1. 首先判断有没有可用的映射
        Optional<ShortUrlMap> urlMpaOptional = getShortUrlMapByUrl(url);
        if (urlMpaOptional.isPresent()) {
            // 1.1 已有，则直接返回
            ShortUrlMap urlMap = urlMpaOptional.get();
            return ResultCodeEnum.SUCCESS.getResultVO(urlMap.getSUrl(), "已有可用的短网址");
        }
        // 2. 判断当前主机是否有可用的号码用于发号
        boolean availableFlag = availableSenderNum();
        if (!availableFlag) {
            // 2.1 不可用，则首先从发号表中获取可用的发号区段信息
            Optional<ShortUrlSenderNum> segmentOptional = senderNumService.createSenderNumSegment();
            if (segmentOptional.isPresent()) {
                ShortUrlSenderNum senderNum = segmentOptional.get();
                // 将区段赋值给该机器
                TMP_START_NUM.set(senderNum.getTmpStartNum());
                TMP_END_NUM.set(senderNum.getTmpEndNum());
            } else {
                // 生成区段失败
                return ResultCodeEnum.ERROR_EXIST_DEPENDENCY_DATA.getResultVO(null, "生成短连接失败，请重试");
            }
        }
        // 代码走到此处，说明发号区段生成成功
        // 可用,则从TMP_START_NUM取一个号码，并+1
        long sendNum = TMP_START_NUM.getAndAdd(ParamConstant.ONE);
        // 对sendNum进行进制转换 10 ---> 62 的短链接
        String shortUrl = ShortURLUtils.toBase62(sendNum, ParamConstant.SIX);
        // 创建url与短链接进行映射封装
        ShortUrlMap shortUrlMap = new ShortUrlMap();
        LocalDateTime now = LocalDateTime.now();
        shortUrlMap.setGmtCreate(now);
        shortUrlMap.setGmtExpiration(now.plusDays(ParamConstant.SEVEN));
        shortUrlMap.setLUrl(url);
        shortUrlMap.setLUrlHash(StringUtils.getMurMurHash_128(url));
        shortUrlMap.setSUrl(shortUrl);
        boolean save = save(shortUrlMap);
        if (save) {
            return ResultCodeEnum.SUCCESS.getResultVO(shortUrl, "生成短连接成功");
        } else {
            return ResultCodeEnum.ERROR_EXIST_DEPENDENCY_DATA.getResultVO(null, "生成短连接失败，请重试");
        }
    }

    @Override
    public Optional<String> getUrlByShortUrl(String shortUrl) {
        ShortUrlMap urlMap = lambdaQuery()
                .select(ShortUrlMap::getLUrl)
                .eq(ShortUrlMap::getSUrl, shortUrl)
                .one();
        if (null == urlMap) {
            return Optional.empty();
        } else {
            return Optional.of(urlMap.getLUrl());
        }
    }

    @Override
    public Optional<ShortUrlMap> getShortUrlMapByUrlHash(String urlHash) {
        return lambdaQuery()
                .select(
                        ShortUrlMap::getLUrl,
                        ShortUrlMap::getLUrl,
                        ShortUrlMap::getLUrlHash)
                .eq(ShortUrlMap::getLUrlHash, urlHash)
                .oneOpt();
    }

    @Override
    public Optional<ShortUrlMap> getShortUrlMapByUrl(String url) {
        // 1. 生成hash
        String urlHash = StringUtils.getMurMurHash_128(url);
        // 2. 根据hash去数据库查询，看是否有映射信息
        return getShortUrlMapByUrlHash(urlHash);
    }

    /**
     * 是否有可用的号码用于发号
     * endUnm > startNum > 0 说明号码的分段可用
     *
     * @return
     */
    private boolean availableSenderNum() {
        long startNum = TMP_START_NUM.get();
        long endNum = TMP_END_NUM.get();
        if (endNum > startNum && startNum > 0) {
            return true;
        }
        return false;
    }
}
