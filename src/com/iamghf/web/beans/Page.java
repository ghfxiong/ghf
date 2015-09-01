package com.iamghf.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
/**
 * ��ҳ
 * 
 * from project: http://www.zyiqibook.com/201505/article0511162118297.html
 */
public class Page<T> implements Serializable {
 
    /**
     * 
     */
    private static final long serialVersionUID = -4906400433425467744L;
 
    private static int DEFAULT_PAGE_SIZE = 20;
 
    /**
     *  ÿҳ��¼����
     */
    private int pageSize = DEFAULT_PAGE_SIZE;
 
    /**
     *  ��ǰҳ��һ��������List�е�λ��,��0��ʼ
     */
    private long start;
     
    private List<T> data; 
 
    private long totalCount; // �ܼ�¼��
 
    /**
     * ���췽��
     */
    public Page() {
        this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
    }
 
    /**
     * Ĭ�Ϲ��췽��.
     *
     * @param start  ��ҳ���������ݿ��е���ʼλ��
     * @param totalSize ���ݿ����ܼ�¼����
     * @param pageSize  ��ҳ����
     * @param data    ��ҳ����������
     */
    public Page(long start, long totalSize, int pageSize, Collection<T> data) {
        this.pageSize = pageSize;
        this.start = start;
        this.totalCount = totalSize;
        this.data = new ArrayList<T>(data);
    }
 
    /**
     * ȡ�ܼ�¼��.
     */
    public long getTotalCount() {
        return this.totalCount;
    }
 
    /**
     * ȡ��ҳ��.
     */
    public long getTotalPageCount() {
        if (pageSize <= 0) {
            return 0;
        }
        return (totalCount + pageSize - 1) / pageSize;
    }
 
    /**
     * ȡÿҳ��������.
     */
    public int getPageSize() {
        return pageSize;
    }
 
    /**
     * ȡ��ǰҳ�еļ�¼.
     */
    public List<T> getResult() {
        return data;
    }
 
    /**
     * ȡ��ҳ��ǰҳ��,ҳ���1��ʼ.
     */
    public int getCurrentPageNo() {
        return (int)(start / pageSize + 1);
    }
 
    /**
     * ��ҳ�Ƿ�����һҳ.
     */
    public boolean hasNextPage() {
        return this.getCurrentPageNo() < this.getTotalPageCount();
    }
 
    /**
     * ��ҳ�Ƿ�����һҳ.
     */
    public boolean hasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }
 
    /**
     * ��ȡ��һҳ��һ�����������ݼ���λ�ã�ÿҳ����ʹ��Ĭ��ֵ.
     */
    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }
 
    /**
     * ��ȡ��һҳ��һ�����������ݼ���λ��.
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }
     
}
