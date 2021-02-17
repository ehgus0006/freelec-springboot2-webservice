package com.jojoidu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository를 상속받고 <Entity, pk타입>을 상속하면 기본적으로 Crud메소드가 자동으로 생성된다
// 주의점은 엔티티 클래스와 엔티티 리포지토리는 함께 위치해야한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("select p from Posts p order by p.id DESC ")
    List<Posts> findAllDesc();
}
