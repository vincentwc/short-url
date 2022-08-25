package com.vincent.utils;

import java.util.List;

public enum ResultCodeEnum {
    SUCCESS(20000, "响应成功"),

    //统一错误码，描述信息接口自定义
    ERROR_CUSTOM(20101, "自定义错误"),
    ERROR_NO_PROJECT_ID(20102,"前端header中无产品id参数"),
    ERROR_REQUEST_HEADER(20103,"请求头错误"),

    //用户相关错误
    ERROR_USER_NOT_EXIST(20201, "用户不存在"),
    ERROR_USER_PASSWORD_ERROR(20202, "用户或密码错误"),
    ERROR_USER_NO_TOKEN(20203, "用户未登录"),
    ERROR_USER_TOKEN_NO_CHECK(20205, "未开启用户校验"),

    //请求参数为空或不合法
    ERROR_REQUEST_PARAM(20301, "请求参数错误"),
    ERROR_REQUEST_URI(20302, "请求路径错误"),
    ERROR_BUG_CATEGORY_EXIST(20303, "存在重复缺陷分类"),
    ERROR_NO_AUTH_RESOURCE(20304, "请求未携带资源信息"),
    ERROR_NO_AUTH_RESOURCE_INVALID(20305, "请求携带资源信息不合法"),
    ERROR_RESOURCE_NAME_EXIST(20306, "请求携带资源信息重复"),

    //数据库结果
    ERROR_RESULT_EMPTY(20401, "查询结果为空"),
    ERROR_DATA_EXIST(20402, "数据已存在"),
    ERROR_EXIST_DEPENDENCY_DATA(20403, "存在依赖数据"),
    ERROR_ADD_DATA(20404, "保存数据失败"),
    ERROR_DEL_DATA(20405, "数据删除失败"),
    ERROR_UPDATE_DATA(20406, "数据更新失败"),
    ERROR_DATABASE_OPERA(20407,"数据库操作失败"),

    // 权限相关
    ERROR_NO_AUTH_OPERATION(20501, "无权限操作"),
    ERROR_NOT_SUPPORT_OPERATION(20502, "不支持该操作"),

    // 远程调用失败
    ERROR_HTTP_TO_CDP(20601, "调用CDP的http接口失败"),
    ERROR_HTTP_FROM_CDP(20602, "CDP调用度量接口推送数据失败"),

    // 文件上传
    FILE_EMPTY(20701,"上传文件为空"),
    FILE_MAX_SIZE(20702,"上传文件超出最大值10M"),
    FILE_UPLOAD_FAIL(20703,"文件上传失败"),
    FILE_REMOVE_FAIL(20704,"文件删除失败"),
    ANY_FILE_UPDATE_FAIL(20705, "部分文件上传失败"),
    ANY_FILE_REMOVE_FAIL(20706, "部分文件删除失败"),
    FILE_PARSE_FAIL(20707, "文件导入解析失败"),
    ANY_FILE_IMPORT_FAIL(20708, "部分文件导入失败"),


    // 字典问题
    NO_SUCH_DICT_CODE(20801,"没有该字典编码"),

    // 服务器连接错误
    ERROR_CONNECTION(20901, "服务器繁忙"),

    //用户成员管理错误
    ERROR_EXCEEDING_USER_LIMIT(20951, "超过人员限制数量"),
    ERROR_ENTERPRISE_CHANGE(20952, "用户已切换企业"),

    //异常错误，统一在切面处理
    ERROR_EXCEPTION(99999, "服务器内部错误");


    private final int code;
    private final String desc;

    ResultCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCodeEnum getResultCodeEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (ResultCodeEnum value : values()) {
            if (value.getCode() == (code)) {
                return value;
            }
        }
        return null;
    }

    public <T> ResultVO<T> getResultVO() {
        return getResultVO((T) null);
    }

    public <T> ResultVO<T> getResultVO(T data) {
        return new ResultVO<>(this, data);
    }

    public <T> ResultVO<T> getResultVO(T data, String message) {
        return new ResultVO<>(this, message, data);
    }

    public <T> ResultVO<T> getResultVO(T data, String message, String tertiaryId, List<String> logDetailList) {
        return new ResultVO<>(this, message, data,tertiaryId, logDetailList);
    }

    public <T> ResultVO<T> getResultVO(T data, String tertiaryId, List<String> logDetailList) {
        return new ResultVO<>(this, data, tertiaryId, logDetailList);
    }


    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
