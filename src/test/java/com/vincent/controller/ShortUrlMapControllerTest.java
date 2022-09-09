package com.vincent.controller;


import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisMapperRegistry;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.vincent.ShortUrlApplication;
import com.vincent.config.ConfigurationDemo;
import com.vincent.entity.ShortUrlMap;
import com.vincent.entity.TestDemo;
import com.vincent.mapper.ShortUrlMapMapper;
import com.vincent.mapper.TestMapper;
import com.vincent.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;
import java.time.LocalDateTime;

/**
 * @author vincent
 * @date 2022/8/26 10:54
 */
@SpringBootTest(classes = ShortUrlApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ShortUrlMapControllerTest {


    @Resource
    private TestMapper testMapper;

    @Resource
    private ITestService testService;

    @Autowired
    private ConfigurationDemo demo;

    @Test
    public void test() {
        TestDemo testDemo = new TestDemo().setName("222222").setAge(1111);
        String name = "111111";
//        testMapper.insert(testDemo);
        UpdateWrapper<TestDemo> wrapper = new UpdateWrapper<TestDemo>()
                .eq("name", name)
                .isNull("age");
        boolean b = testService.saveOrUpdate(testDemo, wrapper);
        System.out.println(b);

    }
}