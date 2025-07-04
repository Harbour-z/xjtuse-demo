package com.example.mybatisplusdemo.model.dto;

import lombok.Data;

@Data
public class PageDTO {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String username;
    private String password;
}