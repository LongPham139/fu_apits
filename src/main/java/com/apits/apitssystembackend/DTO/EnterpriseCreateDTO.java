package com.apits.apitssystembackend.DTO;

import com.apits.apitssystembackend.constant.validation_message.ValidationMessage;
import com.apits.apitssystembackend.constant.validation_size.ValidationSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnterpriseCreateDTO {

    @NotBlank(message = ValidationMessage.NAME_VALID_MESSAGE)
    private String name;

    @NotBlank(message = ValidationMessage.ADDRESS_VALID_MESSAGE)
    private String address;

    @NotBlank(message = ValidationMessage.PHONE_NOT_EMPTY_VALID_MESSAGE)
    @Size(min = ValidationSize.PHONE_MIN, max = ValidationSize.PHONE_MAX, message = ValidationMessage.PHONE_SIZE_VALID_MESSAGE)
    private String phone;
    @NotBlank(message = ValidationMessage.EMAIL_VALID_MESSAGE)
    private String email;

    @NotBlank(message = ValidationMessage.PASSWORD_VALID_MESSAGE)
    private String password;

}
