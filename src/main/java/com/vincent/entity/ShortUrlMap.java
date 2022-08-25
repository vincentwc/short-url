package com.vincent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author vincent
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShortUrlMap implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 长地址
     */
    private String lUrl;

    /**
     * 厂地址的hash值
     */
    private String lUrlHash;

    /**
     * 短地址
     */
    private String sUrl;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 过期时间
     */
    private LocalDateTime gmtExpiration;

}
