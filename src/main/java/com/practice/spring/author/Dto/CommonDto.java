package com.practice.spring.author.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CommonDto {
    private Object result;
    private int status_code;
    private String status_message;

}
