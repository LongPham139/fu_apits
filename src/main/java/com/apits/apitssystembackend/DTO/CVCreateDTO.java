package com.apits.apitssystembackend.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CVCreateDTO {
    private String linkCV;
    private int candiddateId;
}
