package com.apits.apitssystembackend.DTO;

import com.google.auto.value.AutoValue;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@AutoValue.Builder
public class ListRequestClose {
    private List<Integer> listId;
}
