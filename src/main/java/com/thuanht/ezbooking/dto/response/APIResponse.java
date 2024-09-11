package com.thuanht.ezbooking.dto.response;

public class APIResponse<T> {
    boolean success;
    String message;
    T data;
}
