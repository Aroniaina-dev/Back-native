package com.example.sitemada;
// ApiCallback.java
public interface ApiCallback<T> {
    void onSuccess(T data);

    void onError(String errorMessage);
}
