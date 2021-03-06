package com.gardeny.gardenboard.springboot.domain.contents;

import com.gardeny.gardenboard.springboot.domain.account.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void post_detail() {
        //given
        String title = "테스트 게시글";
        Category category = Category.NORMAL;
        String content = "테스트 본문";
        User user = new User();

        postRepository.save(Post.builder()
                                .title(title)
                                .category(category)
                                .content(content)
                                .user(user)
                                .build());

        //when
        List<Post> postList = postRepository.findAll();

        //then
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getCategory()).isEqualTo(Category.NORMAL);
        assertThat(post.getContent()).isEqualTo(content);
    }
}
