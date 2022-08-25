# 短链接发号表
CREATE TABLE `short_url_sender_num`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `gmt_create`    timestamp comment '时间戳信息',
    `tmp_start_num` int(11) unsigned NOT NULL comment '短链起始id',
    `tmp_end_num`   int(11) unsigned NOT NULL comment '短链终止id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

# 短链接映射表
CREATE TABLE `short_url_map`
(
    `id`             int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `l_url`          varchar(160) DEFAULT NULL COMMENT '长地址',
    `l_url_hash`     varchar(60)  DEFAULT NULL COMMENT '长地址hash值',
    `s_url`          varchar(10)  DEFAULT NULL COMMENT '短地址',
    `gmt_create`     timestamp comment '创建时间戳信息',
    `gmt_expiration` timestamp comment '过期时间戳信息',
    UNIQUE INDEX `l_url_hash` (`l_url_hash`) USING BTREE,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;