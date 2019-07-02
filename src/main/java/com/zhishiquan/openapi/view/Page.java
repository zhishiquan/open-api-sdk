package com.zhishiquan.openapi.view;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <b><code>Page</code></b>
 * <p>
 * Comment here.
 * </p>
 * <b>Creation Time:</b> 2016年10月10日 下午2:28:08
 *
 * @author abin.yao
 * @since qlchat 1.0
 */
public class Page {

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

    public Page setLastValue(Long lastValue) {
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

    public Page setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
        return this;
    }

    public Page setPage(int page) {
        this.page = page;
        return this;
    }

    public Page() {
    }

    public Page(int size, int page, long lastValue, Integer totalCount) {
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

    public Page(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public Page(int page, int size, long lastValue) {
        this.page = page;
        this.size = size;
        this.lastValue = lastValue;
    }

    public Page(Long lastValue, int size, boolean hasMore) {
        this.lastValue = lastValue;
        this.size = size;
        this.hasMore = hasMore;
    }

    public Page(Long lastValue, int page, int size, boolean hasMore) {
        this.lastValue = lastValue;
        this.page = page;
        this.size = size;
        this.hasMore = hasMore;
    }

    public Page(Long lastValue, int size, boolean hasMore, long totalCount) {
        this.lastValue = lastValue;
        this.size = size;
        this.hasMore = hasMore;
        this.totalCount = totalCount;
    }

    public Page(int page, int size, boolean hasMore) {
        this.page = page;
        this.size = size;
        this.hasMore = hasMore;
    }

    public Page(int page, int size, boolean hasMore, long totalCount) {
        this.page = page;
        this.size = size;
        this.hasMore = hasMore;
        this.totalCount = totalCount;
    }

    public Page(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Page(Boolean hasMore, int page) {
        this.hasMore = hasMore;
        this.page = page;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public static Page validation(Page page) {
        if (page == null) {
            page = new Page(1, 10);
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
