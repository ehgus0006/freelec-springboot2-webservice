package com.jojoidu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/*
* 
* @RequiredArgsConstructor
* 선언되 모든 final 필드가 포함된 생성자를 자동생성 해준다
* */
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
