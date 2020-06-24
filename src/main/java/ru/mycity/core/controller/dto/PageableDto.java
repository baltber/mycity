package ru.mycity.core.controller.dto;

public class PageableDto<T> {

    private T data;
    private int size;
    private int start;
    private long total;

    public PageableDto(T data, int size, int start, long total) {
        this.data = data;
        this.size = size;
        this.start = start;
        this.total = total;
    }

    public PageableDto() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
