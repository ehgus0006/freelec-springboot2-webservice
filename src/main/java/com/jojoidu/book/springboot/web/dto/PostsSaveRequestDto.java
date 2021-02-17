package com.jojoidu.book.springboot.web.dto;

import com.jojoidu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* Entity나 DTO를 사용할때 @NoargsConstructor(AccessLevel.PROTECTED) 어노테이션을 많이 사용하는 편입니다.
기본 생성자의 접근 제어를 PROTECTED로 설정해놓게 되면 무분별한 객체 생성에 대해 한번 더 체크할 수 있는 수단이 되기 때문입니다.
*
* */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
