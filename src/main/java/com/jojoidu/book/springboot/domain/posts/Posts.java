package com.jojoidu.book.springboot.domain.posts;


import com.jojoidu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

// 롬복 어노테이션
// JPA는 보통 도메인 패키지 안에서 이루어지고 Entity클래스라고 합니다
/*
* @Entity 테이블과 링크될 클래스임을 나타낸다
* @Id pk필드를 나타냅니다
* @GeneratedValue pk의 생성 규칙을 나타낸다, 부트2.0에서는 GenerationType.IDENTITY옵션을 추가해야만 auto_increment가 된다
* @Column 컬럼을 나타내며, 사용하는 이유는 추가로 변경이 필요한 옵션이 있으면 사용합니다.
* 문자열의 경우 vachar2(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나 타입을 Text로 변경하고싶거나 ex:content등의 경우에 사용된다
* @NoArgsConstructor 기본 생성자 자동 추가
* @Getter 클래스 내의 모든 필드의 Getter 메소드를 자동생성
* @Builder 해당 클래스의 빌더 패턴 클래스를 생성
*  */
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }



}
