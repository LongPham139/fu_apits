package com.apits.apitssystembackend.DTO;

import com.apits.apitssystembackend.constant.validation_message.ValidationMessage;
import com.apits.apitssystembackend.constant.validation_size.ValidationSize;
import com.apits.apitssystembackend.entity.Position;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateDTO {
    @NotBlank(message = ValidationMessage.NAME_VALID_MESSAGE)
    private String name;
    private String image;
    @NotBlank(message = ValidationMessage.GENDER_VALID_MESSAGE)
    private String gender;
    @NotBlank(message = ValidationMessage.DOB_VALID_MESSAGE)
    private Date dob;
    @NotBlank(message = ValidationMessage.PHONE_NOT_EMPTY_VALID_MESSAGE)
    @Size(min = ValidationSize.PHONE_MIN, max = ValidationSize.PHONE_MAX, message = ValidationMessage.PHONE_SIZE_VALID_MESSAGE)
    private String phone;
    @NotBlank(message = ValidationMessage.ADDRESS_VALID_MESSAGE)
    private String address;

    @NotBlank(message = ValidationMessage.POSITION_VALID_MESSAGE)
    private String positionName;


}
