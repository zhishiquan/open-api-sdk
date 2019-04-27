package com.zhishiquan.openapi.urls;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AbstractBaseUrl {

    private String name;

    private String url;
}
