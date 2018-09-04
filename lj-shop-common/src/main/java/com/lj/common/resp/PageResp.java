package com.lj.common.resp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoshen.wang on 2018/7/9
 */
public class PageResp <T extends Serializable> implements Serializable{

    private static final long serialVersionUID = 7590532549855145194L;
    private long total; //分页总数

    private List<T> content; //分页查询数据

    public PageResp(List<T> content, long total) {
        this.total = total;
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
