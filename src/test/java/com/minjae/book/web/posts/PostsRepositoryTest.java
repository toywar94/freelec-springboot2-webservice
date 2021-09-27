package com.minjae.book.web.posts;

import com.minjae.book.domain.posts.Posts;
import com.minjae.book.domain.posts.PostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Slf4j
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void testReadBoard(){
        String title = "test title";
        String content = "test content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("minjae@gmail.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        log.info("posts {}",posts);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }


}
