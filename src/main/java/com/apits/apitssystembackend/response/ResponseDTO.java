package com.apits.apitssystembackend.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponseDTO<T> {
    private String message;
    private T data;
    private String status;
}
