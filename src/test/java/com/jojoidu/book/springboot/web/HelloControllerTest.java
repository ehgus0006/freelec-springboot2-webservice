package com.jojoidu.book.springboot.web;



import com.jojoidu.book.springboot.config.auth.SecurityConfig;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
* @RunWith
* 테스트를 실행할 때 JUnit에 내장된 실행자 외에 다른 실행자, SpringRunner라는 스프링 실행자 사용
* 스프링 부트 테스스와 JUnit사이에 연결자 역할을 한다.
*
* @WebMvcTest
* 여러 스프링 테스트 어노테이션중, Web에 집중할수 있는 어노테이션
* 컨트롤러는 사용가능하나, Service, Component, Repository등은 사용 불가능하다
*
* @Autowired
* private MockMvc mvc;
*
* 스프링이 관리하는 빈(bean)을 주입받고
* 웹API를 테스트 할때 사용하고, 스프링 mvc테스트의 시작점, HTTP,GET,POST 등에 대한 API테스트가 가능ㄴ
* */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;



    /*
    * mvc.perform(get("/hello"))
    * MockMvc를 통해 /hello 주소로 http get 요청을 한다, 체이닝이 지원되어 아래와 같이 여러 검증이 가능하다
    *
    * andExpect(status).isOk());
    * mvc.perform의 결과검증, HTTP Header의 Status를 검증, 흔히 알고 잇는 200,404,500등의 상태를 검증합니다.
    * 여기선 OK 즉, 200인지 아닌지 검증
    *
    * andExpect(content().string(hello))
    * mvc.perform의 결과검증, 응답 본문의 내용 검증, Controller에서 hello를 리턴하기 때문에 이값이 맞는지 검증
    * */
    @WithMockUser(roles = "USER")
    @Test
    public void hello_return() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    /*
    * 값을 보낼때 무조건 String 타입만 허용한다
    * String.valueOf(amount)
    * */
    @WithMockUser(roles = "USER")
    @Test
    public void helloDto_return() throws Exception{
        //given
        String name = "hello";
        int amount = 1000;
        //when
        mvc.perform(
                get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

        //then

    }



}