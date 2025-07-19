package com.practice.spring.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonErrorDto {
    private int status_code;
    private String status_message;
}
