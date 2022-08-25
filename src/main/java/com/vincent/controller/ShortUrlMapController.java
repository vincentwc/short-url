package com.vincent.controller;

import com.vincent.service.IShortUrlMapService;
import com.vincent.utils.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author vincent
 */
@Api(value = "短网址服务-映射", tags = "短网址服务-映射")
@Validated
@RestController
@RequestMapping("/short-url-map")
public class ShortUrlMapController {

    @Resource
    private IShortUrlMapService shortUrlMapService;

    /**
     * 生成短网址信息
     *
     * @param url
     * @return
     */
    @PostMapping("/create-short-url")
    @ApiOperation(value = "生成短网址信息", httpMethod = "POST")
    public ResultVO<String> createShortUrl(String url) {
        return shortUrlMapService.createShortUrl(url);
    }

    /**
     * 根据网址链接获取短链接
     *
     * @param url
     * @return
     */
    @PostMapping("/get-short-url-by-url")
    @ApiOperation(value = "获取短网址信息", httpMethod = "POST")
    public ResultVO<String> getShortUrlByUrl(String url) {
        return shortUrlMapService.createShortUrl(url);
    }

    /**
     * 重定向到长链接
     * 重定向 301 永久
     * 302 临时
     *
     * @return
     */
    @GetMapping("/redirect-url")
    @ApiOperation(value = "获取网址信息", httpMethod = "GET")
    public void redirectUrl(HttpServletResponse response, String shortUrl) throws IOException {
        Optional<String> urlOptional = shortUrlMapService.getUrlByShortUrl(shortUrl);
        if (urlOptional.isPresent()) {
            String url = urlOptional.get();
            // 重定向到url的网址
            response.sendRedirect(url);
        }
    }
}
