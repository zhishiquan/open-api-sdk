package com.zhishiquan.openapi.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhishiquan.openapi.enums.ScopeEnum;
import com.zhishiquan.openapi.model.AccessTokenEntity;
import com.zhishiquan.openapi.model.AttendanceCreate;
import com.zhishiquan.openapi.requests.AttendanceCreateRequest;
import com.zhishiquan.openapi.model.AttendanceHideCtrl;
import com.zhishiquan.openapi.requests.AttendanceHideCtrlRequest;
import com.zhishiquan.openapi.requests.Request;
import com.zhishiquan.openapi.responses.AttendanceCreateResponse;
import com.zhishiquan.openapi.responses.AttendanceHideCtrlResponse;
import com.zhishiquan.openapi.urls.AttendanceUrl;
import com.zhishiquan.openapi.urls.CommonUrl;
import com.zhishiquan.openapi.utils.HttpUtils;
import com.zhishiquan.openapi.utils.MD5;
import com.zhishiquan.openapi.utils.StringMapBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 知识圈开放Api调用类
 *
 * @author jl
 */
@Slf4j
@Builder
@AllArgsConstructor(staticName = "of")
public final class OpenApi {

    @NonNull
    private String clientId;

    @NonNull
    private String clientSecret;

    /**
     * 是否沙盒运行~ true - 是, false - 否
     */
    @NonNull
    private boolean isSandbox;

    private String getAccessToken(String scope) {
        if(!TokenCacher.isInit) {
            TokenCacher.init(this);
        }
        String accessToken = TokenCacher.getCache().get(clientId, scope);
        if(StringUtils.isNotBlank(accessToken)) {
            return accessToken;
        }else {
            return TokenCacher.getCache().refresh(clientId, scope);
        }
    }

    /**
     * 获取accessToken
     *
     * @param scope 权限范围
     * @return AccessTokenEntity.class
     */
    protected String getAccessTokenFromRemote(String scope) {
        String result = HttpUtils.form(CommonUrl.accessToken(this.isSandbox).getUrl(), new StringMapBuilder()
                .append("client_id", clientId)
                .append("client_secret", clientSecret)
                .append("grant_type", "client_credentials")
                .append("scope", scope)
                .build());
        AccessTokenEntity accessTokenEntity = JSON.parseObject(result, AccessTokenEntity.class);
        return accessTokenEntity.getAccessToken();
    }

    /**
     * 创建训练营
     * @param attendanceCreate 创建实体
     * @return AttendanceCreateResponse
     */
    public AttendanceCreateResponse createAttendance(AttendanceCreate attendanceCreate) {
        String accessToken = getAccessToken(ScopeEnum.ATTENDANCE.getScope());
        Headers headers = headerWrapper(accessToken);
        AttendanceCreateRequest request = buildRequest(AttendanceCreateRequest.class);
        request.setData(attendanceCreate);
        JSONObject json = HttpUtils.post(AttendanceUrl.create(this.isSandbox).getUrl(), request, headers);
        return json.toJavaObject(AttendanceCreateResponse.class);
    }

    /**
     * 展示/隐藏训练营
     * @param attendanceHideCtrl 展示/隐藏实体
     * @return AttendanceHideCtrlResponse
     */
    public AttendanceHideCtrlResponse attendanceHideCtrl(AttendanceHideCtrl attendanceHideCtrl) {
        String accessToken = getAccessToken(ScopeEnum.ATTENDANCE.getScope());
        Headers headers = headerWrapper(accessToken);
        AttendanceHideCtrlRequest request = buildRequest(AttendanceHideCtrlRequest.class);
        request.setData(attendanceHideCtrl);
        JSONObject json = HttpUtils.post(AttendanceUrl.hideCtrl(this.isSandbox).getUrl(), request, headers);
        return json.toJavaObject(AttendanceHideCtrlResponse.class);
    }

    /**
     * 保证header
     * @param accessToken 调用凭据
     * @return Headers
     */
    private Headers headerWrapper(String accessToken) {
        return new Headers.Builder()
                .add("Authorization", "Bearer ".concat(accessToken))
                .build();

    }

    /**
     * 生成签名
     * @param requestId 请求id
     * @param timestamp 调用时间
     * @return 签名
     */
    private String buildSign(String requestId, Long timestamp) {
        String origin = requestId
                + ":"
                + clientSecret
                + ":"
                + String.valueOf(timestamp);
        return MD5.encode(origin);
    }

    /**
     * 生成请求Request
     * @param requestClass 请求类
     * @param <K> 请求类模板
     * @return <K extends Request>
     */
    private <K extends Request> K buildRequest(Class<K> requestClass) {
        K request = null;
        try {
            String id = UUID.randomUUID().toString();
            Long timestamp = System.currentTimeMillis();
            request = requestClass.newInstance();
            request.setId(id);
            request.setSign(buildSign(request.getId(), timestamp));
            request.setTimestamp(timestamp);
        } catch (InstantiationException | IllegalAccessException  e) {
            log.error("创建Request失败 requestClass:{}", requestClass, e);
        }
        return request;
    }
}
