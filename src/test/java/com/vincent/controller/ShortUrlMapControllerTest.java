package com.vincent.controller;


import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisMapperRegistry;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

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


    @Autowired
    private TestMapper testMapper;

    @Autowired
    private ConfigurationDemo demo;

    @Test
    public void test() {
        TestDemo testDemo = new TestDemo().setName("11111");

//        DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(TestDemo.class);
        IdentifierGenerator identifierGenerator = GlobalConfigUtils.getGlobalConfig(tableInfo.getConfiguration()).getIdentifierGenerator();
        String s = identifierGenerator.nextId(testDemo).toString();
        testDemo.setId(s);
        int insert = testMapper.insert(testDemo);
        System.out.println(s);
        System.out.println(insert);
    }
}