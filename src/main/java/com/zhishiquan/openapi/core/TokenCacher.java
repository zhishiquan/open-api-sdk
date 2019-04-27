package com.zhishiquan.openapi.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zhishiquan.openapi.exception.ExceptionConstants;
import com.zhishiquan.openapi.exception.UserException;
import com.zhishiquan.openapi.model.AccessTokenEntity;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * accessToken缓存
 */
@Slf4j
public final class TokenCacher {
    private final String PREFIX = "AccessToken";

    private static volatile TokenCacher tokenCacher = null;

    private static LoadingCache<String, String> internalCache;

    private static OpenApi openApi;

    public static boolean isInit = false;

    public static void init(OpenApi openApi) {
        TokenCacher.openApi = openApi;
        TokenCacher.isInit = true;
    }

    public static TokenCacher getCache() {
        if (null == tokenCacher) {
            synchronized (TokenCacher.class) {
                if (tokenCacher == null) {
                    tokenCacher = new TokenCacher();
                    internalCache = CacheBuilder.newBuilder()
                            // 缓存1h
                            .expireAfterWrite(1, TimeUnit.HOURS)
                            .build(new CacheLoader<String, String>() {
                                @Override
                                public String load(String key) throws UserException {
                                    String scope = null;
                                    try {
                                        scope = getScopeFromKey(key);
                                    } catch (UserException e) {
                                        throw ExceptionConstants.CANT_GET_SCOPE;
                                    }
                                    return openApi.getAccessTokenFromRemote(scope);
                                }
                            });
                }
            }
        }
        return tokenCacher;
    }

    /**
     * 获取当key的value
     *
     * @param clientId 客户id
     * @param scope    权限范围
     * @return value
     */
    public String get(String clientId, String scope) {
        String cacheKey = build(PREFIX, clientId, scope);
        try {
            return internalCache == null ? null : internalCache.get(cacheKey);
        } catch (Exception e) {
            log.error(MessageFormat.format("get token cache error key:{0}", cacheKey), e);
        }
        return null;
    }

    /**
     * 刷新单个key的配置
     *
     * @param clientId 客户id
     * @param scope    权限范围
     */
    public String refresh(String clientId, String scope) {
        String cacheKey = build(PREFIX, clientId, scope);
        log.info("refresh access_token clientId:{}, scope:{}", clientId, scope);
        internalCache.refresh(cacheKey);
        return get(clientId, scope);
    }

    private String build(String baseKey, String... keys) {
        return baseKey.concat(":")
                .concat(Arrays.toString(keys))
                .replace("[", "")
                .replace("]", "")
                .replace(",", ":")
                .replace(" ", "");
    }

    private String build(String baseKey, Object... keys) {
        return baseKey.concat(":")
                .concat(Arrays.toString(keys))
                .replace("[", "")
                .replace("]", "")
                .replace(",", ":")
                .replace(" ", "");
    }

    private static String getScopeFromKey(String key) throws UserException {
        String[] keyParts = key.split(":");
        if(keyParts.length > 0) {
            return keyParts[2];
        }else {
            throw ExceptionConstants.CANT_GET_SCOPE;
        }
    }
}
