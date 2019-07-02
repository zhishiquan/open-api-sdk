package com.zhishiquan.openapi.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 分页对象
 */
public class PageEntity {

    public static final int MAX_PAGE_SIZE = 50;

    private int page = 0;

    private int size = 10;

    private long totalCount = 0;

    /**
     * 分页时间
     */
    private Long lastValue = 0L;

    private int totalPage = 0;

    private Boolean hasMore = false;

    public int getPage() {
        return page;
    }

    public Long getLastValue() {
        return lastValue;
    }

    public PageEntity setLastValue(Long lastValue) {
        this.lastValue = lastValue;
        return this;
    }

    @JSONField(serialize = false)
    public Integer getOffset() {
        return (page - 1) * size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public PageEntity setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
        return this;
    }

    public PageEntity setPage(int page) {
        this.page = page;
        return this;
    }

    public PageEntity() {
    }

    public PageEntity(int size, int page, long lastValue, Integer totalCount) {
        if (size < 0) {
            size = 20;
        }
        if (page < 1) {
            page = 1;
        }
        if (lastValue < 1) {
            lastValue = 1;
        }
        this.hasMore = false;
        this.size = size;
        this.page = page;
        if (totalCount < 1) {
            this.totalPage = 1;
        } else {
            this.totalPage = (totalCount - 1) / this.size + 1;
        }
    }

    public PageEntity(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PageEntity(int page, int size, long lastValue) {
        this.page = page;
        this.size = size;
        this.lastValue = lastValue;
    }

    public PageEntity(Long lastValue, int size, boolean hasMore) {
        this.lastValue = lastValue;
        this.size = size;
        this.hasMore = hasMore;
    }

    public PageEntity(Long lastValue, int page, int size, boolean hasMore) {
        this.lastValue = lastValue;
        this.page = page;
        this.size = size;
        this.hasMore = hasMore;
    }

    public PageEntity(Long lastValue, int size, boolean hasMore, long totalCount) {
        this.lastValue = lastValue;
        this.size = size;
        this.hasMore = hasMore;
        this.totalCount = totalCount;
    }

    public PageEntity(int page, int size, boolean hasMore) {
        this.page = page;
        this.size = size;
        this.hasMore = hasMore;
    }

    public PageEntity(int page, int size, boolean hasMore, long totalCount) {
        this.page = page;
        this.size = size;
        this.hasMore = hasMore;
        this.totalCount = totalCount;
    }

    public PageEntity(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public PageEntity(Boolean hasMore, int page) {
        this.hasMore = hasMore;
        this.page = page;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public static PageEntity validation(PageEntity page) {
        if (page == null) {
            page = new PageEntity(1, 10);
        } else {
            if (page.getPage() < 1) {
                page.setPage(1);
            }
            if (page.getSize() < 0 || page.getSize() > 50) {
                page.setSize(10);
            }
        }
        return page;
    }
}
