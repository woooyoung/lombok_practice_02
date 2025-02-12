package com.koreait.lombok_practice_02;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeContoller {
    @GetMapping("a")
    @ResponseBody
    public String plus(@RequestParam("a") String num1Str, @RequestParam("b") String num2Str) {
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);

        System.out.println("a : " + num1Str);
        System.out.println("b : " + num2Str);

        return "a + b = %d".formatted(num1 + num2);
    }

    @GetMapping("b")
    @ResponseBody
    public String plus(
            //http://localhost:8080/b?a=10&b=20
            @RequestParam("a") int num1,
            @RequestParam("b") int num2,
            @RequestParam(name = "c", defaultValue = "10") int num3
    ) {

        System.out.println("a : " + num1);
        System.out.println("b : " + num2);
        System.out.println("c : " + num3);

        return "2: a + b + c = %d".formatted(num1 + num2 + num3);
    }

    @GetMapping("c")
    @ResponseBody
    public String c(
            Boolean isMarried
    ) {
        if (isMarried == null) return "정보 입력해";

        return isMarried ? "Married" : "Unmarried";
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public static class Person {
        private String name;
        private int age;
    }

    @GetMapping("person")
    @ResponseBody
    public String person1(
            String name,
            int age
    ) {
        Person person = new Person(name, age);

        return person.toString();
    }

    @GetMapping("person2")
    @ResponseBody
    public String person1(
            Person person
    ) {
        return person.toString();
    }

    @AllArgsConstructor
    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Post {
        @ToString.Exclude
        @JsonIgnore
        @EqualsAndHashCode.Include
        private Long id;
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;
        @Builder.Default
        private String subject = "제목이야";
        private String body;
    }

    @GetMapping("/posts")
    @ResponseBody

    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>() {{
            add(new Post(1L, LocalDateTime.now(), LocalDateTime.now(), "제목1", "내용1"));
            add(new Post(2L, LocalDateTime.now(), LocalDateTime.now(), "제목2", "내용2"));
            add(new Post(3L, LocalDateTime.now(), LocalDateTime.now(), "제목3", "내용3"));
        }};
        return posts;
    }

    @GetMapping("/posts2")
    @ResponseBody
    public List<Post> getPosts2() {
        List<Post> posts = new ArrayList<>() {{
//            add(new Post(1L, LocalDateTime.now(), LocalDateTime.now(), "제목1", "내용1"));
            add(
                    Post.builder()
                            .id(1L)
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .body("내용1")
                            .build()
            );
            add(
                    Post.builder()
                            .id(2L)
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .subject("제목2")
                            .body("내용2")
                            .build()
            );
            add(
                    Post.builder()
                            .id(3L)
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .subject("제목3")
                            .body("내용3")
                            .build()
            );
        }};
        return posts;
    }

    @GetMapping("/post/1")
    @ResponseBody
    public Post getPost() {
        Post post = Post.builder()
                .id(1L)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .body("내용1")
                .build();

        System.out.println(post);
        return post;
    }

}
