package com.zhishiquan.openapi.exception;

/**
 * 异常
 * @author jl
 */
public interface ExceptionConstants {
    UserException CLIENT_NOT_FOUND = new UserException("CLIENT_NOT_FOUND", "找不到客户信息");

    UserException CANT_GET_SCOPE = new UserException("CANT_GET_SCOPE", "获取scope失败");

}
