package com.vincent.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


/**
 * @author admin
 * @param <T>
 */
@ApiModel(description = "全局的统一返回结果")
@Data
@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "desc", "message", "data"})
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 3068837394742385883L;

    /**
     * 错误码.
     */
    @ApiModelProperty(value = "状态码", example = "20000")
    @JSONField(ordinal = 1)
    private Integer code;

    /**
     * 状态码描述.
     */
    @ApiModelProperty(value = "状态码描述", example = "调用成功")
    @JSONField(ordinal = 2)
    private String desc;

    /**
     * 提示信息.
     */
    @ApiModelProperty(value = "返回信息", example = "调用成功")
    @JSONField(ordinal = 3)
    private String message;

    /**
     * 具体内容.
     */
    @ApiModelProperty(value = "返回的数据")
    @JSONField(ordinal = 4)
    private T data;

    /**
     * 日志描述.
     */
    @ApiModelProperty(value = "第三级ID，如代码库ID", example = "123")
    @JSONField(ordinal = 5)
    private String tertiaryId;

    /**
     * 日志描述.
     */
    @ApiModelProperty(value = "日志描述", example = "【cdp】")
    @JSONField(ordinal = 6)
    private List<String> logDetailList;

    public ResultVO() {
    }

    public ResultVO(Integer code, String desc) {
        this(code, desc, desc);
    }

    public ResultVO(Integer code, String desc, String message) {
        this.code = code;
        this.desc = desc;
        this.message = message;
    }

    public ResultVO(Integer code, String desc, String message, String tertiaryId, List<String> logDetailList) {
        this.code = code;
        this.desc = desc;
        this.message = message;
        this.tertiaryId = tertiaryId;
        this.logDetailList = logDetailList;
    }

    public ResultVO(T data) {
        this(ResultCodeEnum.SUCCESS, data);
    }

    public ResultVO(ResultCodeEnum resultCode, T data) {
        this(resultCode, resultCode.getDesc(), data);
    }

    public ResultVO(ResultCodeEnum resultCode, String message, T data) {
        this(resultCode.getCode(), resultCode.getDesc(), message);
        this.data = data;
    }

    public ResultVO(ResultCodeEnum resultCode, String message, T data, String tertiaryId, List<String> logDetailList) {
        this(resultCode.getCode(), resultCode.getDesc(), message, tertiaryId, logDetailList);
        this.data = data;
    }

    public ResultVO(ResultCodeEnum resultCode, T data, String tertiaryId, List<String> logDetailList) {
        this(resultCode.getCode(), resultCode.getDesc());
        this.data = data;
        this.tertiaryId = tertiaryId;
        this.logDetailList = logDetailList;
    }

    public ResultVO<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultVO<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultVO<T> message(String message, Object... args) {
        this.setMessage(String.format(message, args));
        return this;
    }

    public ResultVO<T> code(String tertiaryId, List<String> logDetailList) {
        this.setTertiaryId(tertiaryId);
        this.setLogDetailList(logDetailList);
        return this;
    }

    public ResultVO<T> data(T data) {
        this.setData(data);
        return this;
    }

    public boolean isSuccess() {
        return this.code == ResultCodeEnum.SUCCESS.getCode();
    }

    /**
     * 检查resultVO是否为成功结果
     * @param resultVO
     * @return
     */
    public static boolean checkSuccess(ResultVO resultVO) {
        if (resultVO == null) {
            return false;
        }

        return ResultCodeEnum.SUCCESS.getCode() == resultVO.getCode();
    }

    /**
     * 检查resultVO是否为成功结果
     * @param resultVO
     * @return
     */
    public static <T> T checkSuccessData(ResultVO<T> resultVO) {
        if (resultVO == null) {
            return null;
        }

        if (ResultCodeEnum.SUCCESS.getCode() != resultVO.getCode()) {
            return null;
        }

        return resultVO.getData();
    }

    /**
     * 检查resultVO包含data数据时
     * @param resultVO
     * @return
     */
    public static <T> T checkSuccess(ResultVO resultVO, Class<T> clazz) {
        if (resultVO == null) {
            return null;
        }

        //检查code编码是否为成功的编号
        if (ResultCodeEnum.SUCCESS.getCode() == resultVO.getCode()) {
            return JSONObject.parseObject(JSON.toJSONString(resultVO.getData()), clazz);
        }

        return null;
    }
}

