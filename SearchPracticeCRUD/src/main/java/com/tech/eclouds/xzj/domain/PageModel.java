package com.tech.eclouds.xzj.domain;

import com.tech.eclouds.xzj.common.Constant;

import java.io.Serializable;
import java.util.List;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/16
 */
public class PageModel<T> implements Serializable {
    private long total;
    private long totalPages;
    private int form = 0;
    private int size = Constant.DEFALT_PAGE_SIZE;
    private List<T> resultList;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }


    public int getForm() {
        return form < 0 ? 0 : form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageModel{");
        sb.append("total=").append(total);
        sb.append(", totalPages=").append(totalPages);
        sb.append(", form=").append(form);
        sb.append(", size=").append(size);
        sb.append(", resultList=").append(resultList);
        sb.append('}');
        return sb.toString();
    }
}
