package ru.mycity.core.service.dao.model;

public class QuerryResult<T> {

    private T t;
    private int size;
    private int start;
    private long total;

    public QuerryResult(T t, int size, int start, long total) {
        this.t = t;
        this.size = size;
        this.start = start;
        this.total = total;
    }

    public QuerryResult() {
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
