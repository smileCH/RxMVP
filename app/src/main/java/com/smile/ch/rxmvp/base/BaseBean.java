package com.smile.ch.rxmvp.base;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：基类bean
 */

public class BaseBean<T> {
    private int status;
    private String message;
    private T results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
