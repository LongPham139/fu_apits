package com.apits.apitssystembackend.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseWithTotalPage<T> {

    private int totalPage;
    private List<T> responseList;
}
