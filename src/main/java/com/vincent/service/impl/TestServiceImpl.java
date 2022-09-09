package com.vincent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cecloud.cdp.shorturl.utils.ParamConstant;
import com.cecloud.cdp.shorturl.utils.ShortURLUtils;
import com.cecloud.cdp.shorturl.utils.StringUtils;
import com.vincent.entity.ShortUrlMap;
import com.vincent.entity.ShortUrlSenderNum;
import com.vincent.entity.TestDemo;
import com.vincent.mapper.ShortUrlMapMapper;
import com.vincent.mapper.TestMapper;
import com.vincent.service.IShortUrlMapService;
import com.vincent.service.IShortUrlSenderNumService;
import com.vincent.service.ITestService;
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
public class TestServiceImpl extends ServiceImpl<TestMapper, TestDemo> implements ITestService {


}
