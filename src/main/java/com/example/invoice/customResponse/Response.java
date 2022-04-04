package com.example.invoice.customResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    public String status;
    public String message;
    public T data;

    public Response(String status, T data) {
        this.status = status;
        this.data = data;
    }
}
