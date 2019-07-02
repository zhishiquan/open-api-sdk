package com.zhishiquan.openapi.view;


import java.io.Serializable;

/**
 * 页面视图基类
 * Created by jason on 17-4-11.
 */
public class BasePageView implements Serializable {

    private static final long serialVersionUID = 7307678206445773257L;

    private Page page;

    public Page getPage() {
        return page;
    }

    public BasePageView setPage(Page page) {
        this.page = page;
        return this;
    }
}
