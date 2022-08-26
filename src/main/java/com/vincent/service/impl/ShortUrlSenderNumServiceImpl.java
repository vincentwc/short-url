package com.vincent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cecloud.cdp.shorturl.utils.ParamConstant;
import com.vincent.config.SenderNumProperties;
import com.vincent.entity.ShortUrlSenderNum;
import com.vincent.mapper.ShortUrlSenderNumMapper;
import com.vincent.service.IShortUrlSenderNumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author vincent
 */
@Service
public class ShortUrlSenderNumServiceImpl extends ServiceImpl<ShortUrlSenderNumMapper, ShortUrlSenderNum> implements IShortUrlSenderNumService {


    @Resource
    private SenderNumProperties numProperties;

    @Override
    public Optional<ShortUrlSenderNum> createSenderNumSegment() {
        // 1. 获取现有数据库中正在使用的最大区段信息的结束num,由于是id自增,id最大的就是最大的区段
        ShortUrlSenderNum oldSenderNum = lambdaQuery()
                .select(ShortUrlSenderNum::getTmpEndNum)
                .orderByDesc(ShortUrlSenderNum::getId)
                .one();
        Integer tmpEndNum;
        if (null == oldSenderNum) {
            // 则说明数据库中没有，从0开始
            tmpEndNum = ParamConstant.ZERO;
        } else {
            tmpEndNum = oldSenderNum.getTmpEndNum();
        }
        // 2. 创建新的号段区间信息并入库保存
        int segment = numProperties.getSegment();
        ShortUrlSenderNum newSenderNum = new ShortUrlSenderNum();
        newSenderNum.setTmpStartNum(++tmpEndNum);
        newSenderNum.setTmpEndNum(tmpEndNum + segment);
        newSenderNum.setGmtCreate(LocalDateTime.now());
        // 3. 保存入库
        boolean save = save(newSenderNum);
        if (save) {
            return Optional.of(newSenderNum);
        } else {
            return Optional.empty();
        }
    }
}
