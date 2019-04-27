package com.zhishiquan.openapi.utils;
import java.util.HashMap;
import java.util.Map;

/**
 * map builder
 * Created by ljj on 2018/04/08.
 */
public class CustomMapBuilder<K, V> extends HashMap<K, V> {
    public CustomMapBuilder<K, V> append(K paramKey, V paramValue) {
        this.put(paramKey, paramValue);
        return this;
    }
    public Map<K, V> getParams() {
        return this;
    }
    public Map<K, V> build() {
        return this;
    }
}
